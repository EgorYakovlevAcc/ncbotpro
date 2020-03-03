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
import java.util.stream.Collectors;

@Service
@Transactional
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
    public Question findFirstQuestion() {
        return questionRepository.findAll().stream()
                .findFirst()
                .orElse(null);
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
        return questionOptionsAnswer;
    }

    @Override
    public Integer getCorrectIndexOfOptionByAnswer(Question question, Answer answer) {
        return null;
    }

    @Override
    public void editQuestionWithOptions(QuestionOptionsAnswer questionOptionsAnswer) {
        Question question = questionRepository.findQuestionById(questionOptionsAnswer.getId());
        question.setContent(questionOptionsAnswer.getContent());
        question.getAnswer().setContent(questionOptionsAnswer.getAnswer());
        optionService.editOptionsByQuestionAndContents(questionOptionsAnswer.getId(), questionOptionsAnswer.getOptions().stream()
                .map(option -> option.getContent())
                .collect(Collectors.toList()));
    }

}
