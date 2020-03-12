package com.ncquizbot.ncbot.service.impl;

import com.ncquizbot.ncbot.model.Answer;
import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.pojo.Option;
import com.ncquizbot.ncbot.pojo.QuestionOptionsAnswer;
import com.ncquizbot.ncbot.repo.QuestionRepository;
import com.ncquizbot.ncbot.service.AnswerService;
import com.ncquizbot.ncbot.service.OptionService;
import com.ncquizbot.ncbot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private OptionService optionService;

    @Override
    public Question findQuestionById(Integer id) {
        return questionRepository.findQuestionById(id);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public void createQuestionWithOptionsAndAnswer(QuestionOptionsAnswer questionOptionsAnswer) {
        Question question = new Question();
        System.out.println("EGORKA = " + questionOptionsAnswer.getContent());
        question.setWeight(questionOptionsAnswer.getWeight());
        question.setContent(questionOptionsAnswer.getContent());
        String contentOfAnswer = questionOptionsAnswer.getAnswer();
        questionRepository.save(question);
        optionService.createOptionsByQuestionAndContents(question.getId(), questionOptionsAnswer.getOptions().stream()
                .map(opt -> opt.getContent())
                .collect(Collectors.toList()));
        answerService.createAnswersByContents(question, contentOfAnswer);
    }

    @Override
    public Question getNextQuestion(Integer currentQuestionId) {
        List<Question> questions = questionRepository.findAll();
        Integer currentQuestionIndex = questions.stream()
                .map(question -> question.getId())
                .collect(Collectors.toList())
                .indexOf(currentQuestionId);
        if ((currentQuestionIndex < questions.size() - 1) && (Objects.nonNull(currentQuestionId))) {
            Question question = questions.get(currentQuestionIndex + 1);
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Question getNextQuestionByWeight(Integer weight) {
        List<Question> questionsByWeight = questionRepository.findQuestionsByWeight(weight + 1);
        if (questionsByWeight.isEmpty()){
            return null;
        }
        Random random = new Random();
        int sizeOfQuestionsList = questionsByWeight.size();
        int indexOfQuestion = random.nextInt(sizeOfQuestionsList);
        return questionsByWeight.get(indexOfQuestion);
    }

    @Override
    public Question findFirstQuestion() {
        List<Question> questions =  questionRepository.findQuestionsByWeight(1);
        Random random = new Random();
        int questionIndex = random.nextInt(questions.size());
        return questions.get(questionIndex);

    }

    @Override
    public void deleteQuestionById(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionOptionsAnswer convertQuestionToQuestionWithOptions(Question question) {
        QuestionOptionsAnswer questionOptionsAnswer = new QuestionOptionsAnswer();
        questionOptionsAnswer.setOptions(question.getOptions().stream()
                .map(option -> option.getContent())
                .map(optionContent -> new Option(optionContent))
                .collect(Collectors.toList()));
        questionOptionsAnswer.setId(question.getId());
        questionOptionsAnswer.setContent(question.getContent());
        questionOptionsAnswer.setAnswer(question.getAnswer().getContent());
        questionOptionsAnswer.setWeight(question.getWeight());
        return questionOptionsAnswer;
    }

    @Override
    public Integer getCorrectIndexOfOptionByAnswer(Question question, Answer answer) {
        return null;
    }

    @Override
    @Transactional
    public void editQuestionWithOptions(QuestionOptionsAnswer questionOptionsAnswer) {
        Question question = questionRepository.findQuestionById(questionOptionsAnswer.getId());
        question.setContent(questionOptionsAnswer.getContent());
        question.setWeight(questionOptionsAnswer.getWeight());
        question.getAnswer().setContent(questionOptionsAnswer.getAnswer());
//        List<com.ncquizbot.ncbot.model.Option> options = questionOptionsAnswer.getOptions().stream().map(x -> (new com.ncquizbot.ncbot.model.Option(x.getContent()))).collect(Collectors.toList());
//        question.setOptions(options);
        save(question);
//        optionService.editOptionsByQuestionAndContents(questionOptionsAnswer.getId(),  question.getOptions(), questionOptionsAnswer.getOptions());
    }

    @Override
    public List<Question> findQuestionsByWeight(Integer weight) {
        return questionRepository.findQuestionsByWeight(weight);
    }

}
