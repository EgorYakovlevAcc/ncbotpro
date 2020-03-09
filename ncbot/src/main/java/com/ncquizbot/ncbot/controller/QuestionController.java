package com.ncquizbot.ncbot.controller;

import com.ncquizbot.ncbot.model.Question;
import com.ncquizbot.ncbot.pojo.Option;
import com.ncquizbot.ncbot.pojo.QuestionAndOptions;
import com.ncquizbot.ncbot.pojo.QuestionOptionsAnswer;
import com.ncquizbot.ncbot.service.OptionService;
import com.ncquizbot.ncbot.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/questions")
@CrossOrigin()
public class QuestionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private QuestionService questionService;
    @Autowired
    private OptionService optionService;

    @GetMapping(value = {"/all"})
    public String getQuestions(Model model) {
        System.out.println("getQuestions");
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "questions";
    }

    @GetMapping(value = {"/all/text"})
    @ResponseBody
    public List<QuestionOptionsAnswer> getQuestionsText(Model model) {
        List<Question> questions = questionService.findAll();
        return questions.stream()
                .map(question -> questionService.convertQuestionToQuestionWithOptions(question))
                .collect(Collectors.toList());
    }

    @GetMapping(value = {"/add"})
    public String getAddQuestion(Model model) {
        System.out.println("getAddQuestion");
        if (Objects.isNull(model.getAttribute("questionAndOption"))) {
            QuestionAndOptions questionAndOptions = new QuestionAndOptions();
            model.addAttribute("questionAndOption", questionAndOptions);
            model.addAttribute("buttonValue", "createQuestion");
        }
        return "add_question";
    }

    @PostMapping(value = "/add")
    public ResponseEntity postAddQuestion(@RequestBody QuestionOptionsAnswer questionOptionsAnswer){
        questionService.createQuestionWithOptionsAndAnswer(questionOptionsAnswer);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/edit")
    @ResponseBody
    public QuestionOptionsAnswer getEditQuestion(Model model, @RequestParam("id") Integer questionId) {
        Question question = questionService.findQuestionById(questionId);
        QuestionOptionsAnswer questionOptionsAndAnswer = new QuestionOptionsAnswer();
        questionOptionsAndAnswer.setContent(question.getContent());
        List<Option> optionsPojo = question.getOptions().stream()
                .map(option -> optionService.convertOptionModelToOptionPojo(option))
                .collect(Collectors.toList());
        questionOptionsAndAnswer.setOptions(optionsPojo);
        return questionOptionsAndAnswer;
    }

    @PostMapping(value = "/edit")
    public ResponseEntity postEditQuestion(@RequestBody QuestionOptionsAnswer questionOptionsAnswer) {
        System.out.println(questionOptionsAnswer.getId());
        questionService.editQuestionWithOptions(questionOptionsAnswer);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/remove")
    public ResponseEntity editQuestion(Model model, @RequestParam("id") Integer questionId) {
        questionService.deleteQuestionById(questionId);
        return ResponseEntity.ok(null);
    }
}
