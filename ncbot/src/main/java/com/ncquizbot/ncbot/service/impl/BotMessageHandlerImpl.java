package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.bot.Bot;
import com.ncquizbot.ncbot.bot.MessagesPackage;
import com.ncquizbot.ncbot.model.Answer;
import com.ncquizbot.ncbot.model.Option;
import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.model.User;
import com.ncquizbot.ncbot.pojo.HelloGoodbyeMessages;
import com.ncquizbot.ncbot.qrcode.QrCodeGenerator;
import com.ncquizbot.ncbot.qrcode.QrCodeGeneratorImpl;
import com.ncquizbot.ncbot.service.BotMessageHandler;
import com.ncquizbot.ncbot.service.QuestionService;
import com.ncquizbot.ncbot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class BotMessageHandlerImpl implements BotMessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
    private static final String HELLO_MESSAGE = "Привет! \n" +
            "Давай знакомиться &#128512; \n" +
            "Я – телеграм бот компании Netcracker. \n" +
            "И сегодня у тебя есть шанс проверить свои знания и логику, а также получить призы от нас. \n" +
            "После прохождения всех заданий обязательно подходи к стенду Netcracker за призом. \n" +
            "Обращаем внимание, что приятные подарочки получат самые быстрые из вас! &#128521;";
    public static final String GOODBYE_MESSAGE = "\nОбменять полученные баллы на призы можно у стенда Netcracker на Найти ИТ уже сейчас. \n" +
            "Если ты хочешь начать карьеру в IT-сфере, то подавай заявку до 31 марта на бесплатное обучение у нас: http://msk.edu-netcracker.com. \n" +
            "Учебный Центр Netcracker проводит курсы по таким направлениям как Enterprise Development, Business Analysis, Technical Sales, Devops и т.д.";
    public static final String USER_SCORE = "Thank you it was last question. Your score is ";
    public static final String COMMAND_PRESENT = "present";
    public static final String COMMAND_GO = "go";
    public static final String FINISH_GAME = "You have finished your game! We will happy to meet you at another time";
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @Override
    public MessagesPackage handleMessage(Update update) {
        LOGGER.info("handleMessage [START]");
        Message message = update.getMessage();
        if (Objects.nonNull(message) && message.hasText()) {
            return handleInputMessage(message);
        }
        if (update.hasCallbackQuery()) {
            return handelCallbackQuery(update.getCallbackQuery());
        }
        return null;
    }

    private MessagesPackage handelCallbackQuery(CallbackQuery callbackQuery) {
        LOGGER.info("handelCallbackQuery [START]");
        User user = userService.findUserByTelegramId(callbackQuery.getFrom().getId());
        MessagesPackage messagesPackage = null;
        Long chatId = callbackQuery.getMessage().getChatId();
        String outputText = callbackQuery.getData();
        switch (outputText) {
            case COMMAND_GO: {
                messagesPackage = handleGoCommand(user);
                break;
            }
            case COMMAND_PRESENT: {
                messagesPackage = handlePresentCommand(user);
                break;
            }
            default: {
                messagesPackage = handleAnswerAndGenerateAnswer(user, outputText);
                break;
            }
        }
        return messagesPackage;
    }

    private MessagesPackage handleStartCommand(User user) {
        LOGGER.info("handleStartCommand [START]");
        String ouputMessageText = HelloGoodbyeMessages.HELLO_MESSAGE.text;
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> keyboardRowList = new ArrayList<>();
        InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
        keyboardButton.setText(COMMAND_GO);
        keyboardButton.setCallbackData(COMMAND_GO);
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);
        inlineKeyboardMarkup.setKeyboard(keyboardRowList);
        return getSendMessageForBot(ouputMessageText, user.getChatId(), inlineKeyboardMarkup, null);
    }

    private MessagesPackage handleGoCommand(User user) {
        LOGGER.info("handleGoCommand [START]");
        userService.setActiveStatusTrue(user);
        return getNextQuestionForUser(user);
    }

    private MessagesPackage handleInputMessage(Message message) {
        LOGGER.info("handleInputMessage [START]");
        MessagesPackage messagesPackage = new MessagesPackage();
        if (Objects.nonNull(message) && message.hasText()) {
            User user = userService.createAndSaveUserByTelegramMessageIfCurrentDoesNotExist(message);
            if (!user.isActiveNow()) {
                messagesPackage.addMessagesToPackage(handleStartCommand(user).getMessages());
            }
        }
        return messagesPackage;
    }

    private MessagesPackage handlePresentCommand(User user) {
        LOGGER.info("handlePresentCommand [START]");
        MessagesPackage messagesPackage = new MessagesPackage();
        if (user.isGameOver()) {
            if (!user.isPresentGiven()) {
                return messagesPackage.addMessageToPackage(getQrCodeImageForPresent(user));
            }
        }
        return messagesPackage;
    }

    private MessagesPackage handleAnswerAndGenerateAnswer(User user, String currentMessageText) {
        LOGGER.info("handleAnswerAndGenerateAnswer [START]");
        MessagesPackage messagesPackage = new MessagesPackage();
        String ouputMessageText = "";
        InlineKeyboardMarkup inlineKeyboardMarkup = null;
        if (user.isActiveNow()) {
            userService.updateLastUserSessionDate(user);
            if (user.getQuestionNumber() > 0) {
                String outputTextMessage = updateUserScore(user, currentMessageText);
                if (Objects.nonNull(outputTextMessage)) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(user.getChatId())
                            .setText(outputTextMessage);
                    messagesPackage.addMessageToPackage(sendMessage);
                }
            }
            return messagesPackage.addMessagesToPackage(getNextQuestionForUser(user).getMessages());
        } else {
            ouputMessageText = HelloGoodbyeMessages.GOODBYE_MESSAGE.text;
            return getSendMessageForBot(ouputMessageText, user.getChatId(), inlineKeyboardMarkup, null);
        }
    }

    private MessagesPackage getNextQuestionForUser(User user) {
        LOGGER.info("getNextQuestionForUser [START]");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        Question nextQuestion = getQuestionForUser(user);
        //end [START]
        if (Objects.isNull(nextQuestion)) {
            String ouputMessageText = getGoodByeMessage(user);
            return getSendMessageForBot(ouputMessageText, user.getChatId(), inlineKeyboardMarkup, null);
        }
        //end [FINISH]
        else {
            if (nextQuestion.getOptions().size() > 1) {
                inlineKeyboardMarkup = getQuestionWithMultipleOptions(nextQuestion.getOptions());
            }
            return getSendMessageForBot(nextQuestion.getContent(), user.getChatId(), inlineKeyboardMarkup, nextQuestion.getAttachement());
        }
    }

    private String updateUserScore(User user, String userAnswerText) {
        LOGGER.info("updateUserScore [START]");
        Question lastQuestion = questionService.findQuestionById(user.getCurrentQuestionId());
        Integer questionWeight = lastQuestion.getWeight();
        Answer answer = lastQuestion.getAnswer();
        String answerText = Objects.isNull(answer) ? "" : answer.getContent();
        if (checkAnswer(userAnswerText, answerText)) {
            userService.increaseUserScore(user, 1);
        }
        return lastQuestion.getOptions().stream()
                .filter(option -> option.getContent().equals(userAnswerText))
                .map(option -> option.getReaction())
                .findFirst()
                .orElse(null);
    }

    private MessagesPackage getSendMessageForBot(String content, Long chatId, InlineKeyboardMarkup replyKeyboardMarkup, byte[] attachment) {
        LOGGER.info("getSendMessageForBot [START]");
        MessagesPackage messagesPackage = new MessagesPackage();
        SendMessage sendMessage = null;
        if (Objects.nonNull(content)) {
            sendMessage = new SendMessage();
            sendMessage.setText(content)
                    .setChatId(chatId);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        if (attachment != null) {
            InputStream photoInputStream = new ByteArrayInputStream(attachment);
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto
                    .setChatId(chatId)
                    .setNewPhoto("photo_" + chatId, photoInputStream);
            messagesPackage.addMessageToPackage(sendPhoto);
        }
        messagesPackage.addMessageToPackage(sendMessage);
        return messagesPackage;
    }

    private SendPhoto getQrCodeImageForPresent(User user) {
        QrCodeGenerator qrCodeGenerator = new QrCodeGeneratorImpl();
        qrCodeGenerator.processGeneratingQrCode(user);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(qrCodeGenerator.getQrCode());
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(user.getChatId())
                .setNewPhoto("qr code for present", byteArrayInputStream);
        return sendPhoto;
    }

    private String getGoodByeMessage(User user) {
        LOGGER.info("getGoodByeMessage [START]");
        userService.turnOffUserActivityStatus(user);
        userService.updateUserSessionEndDate(user);
        userService.setGameOverForUser(user);
        return getGoodbyeMessage(user.getScore());
    }

    private Question getQuestionForUser(User user) {
        LOGGER.info("getQuestionForUser [START]");
        return userService.setNextQuestionToUser(user);
    }

    private InlineKeyboardMarkup getQuestionWithMultipleOptions(List<Option> options) {
        LOGGER.info("getQuestionWithMultipleOptions [START]");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
        for (Option option : options) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setCallbackData(option.getContent());
            inlineKeyboardButton.setText(option.getContent());
            keyboardRow.add(inlineKeyboardButton);
        }
        keyboardRowList.add(keyboardRow);
        inlineKeyboardMarkup.setKeyboard(keyboardRowList);
        return inlineKeyboardMarkup;
    }

    private boolean checkAnswer(String userAnswer, String answer) {
        userAnswer = userAnswer.toLowerCase();
        answer = answer.toLowerCase();
        if (userAnswer.equals(answer)) {
            return true;
        }
        return false;
    }

    private String getGoodbyeMessage(Integer userScore) {
        return USER_SCORE + userScore + GOODBYE_MESSAGE;
    }
}
