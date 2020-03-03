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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/questions")
@CrossOrigin
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
//    @PostMapping(value = "/add", params = {"action"})
//    public String postAddQuestion(@RequestParam(value = "answer_index", required = false) Integer answerIndex, @RequestParam("action") String action, ModelMap model, @ModelAttribute("questionAndOptions") QuestionAndOptions questionAndOptions) {
//        if (Objects.isNull(questionAndOptions.getOptions())) {
//            List<String> options = new ArrayList<>();
//            questionAndOptions.setOptions(options);
//        }
//        if (action.equals("createQuestion")) {
//            questionAndOptions.getOptions().removeAll(questionAndOptions.getOptions().stream()
//                    .filter(StringUtils::isEmpty)
//                    .collect(Collectors.toList()));
//            questionService.createQuestionWithOptionsAndAnswer(questionAndOptions, answerIndex);
//            return "redirect:/questions/all";
//        }
//        if (action.equals("addOption")) {
//            System.out.println("Egor");
//            List<String> options = new ArrayList<>();
//            options.addAll(questionAndOptions.getOptions());
//            options.add("");
//            questionAndOptions.setOptions(options);
//            model.addAttribute("questionAndOption", questionAndOptions);
//            model.addAttribute("buttonValue", "editQuestion");
//            model.addAttribute("buttonName", "edit question");
//            System.out.println(questionAndOptions.getOptions());
//            return "add_question";
//        } else {
//            model.addAttribute("questionAndOption", questionAndOptions);
//            return "add_question";
//        }
//
//    }

//    @PostMapping(value = "/add", params = {"removeOption"})
//    public String revomeOptionToQuestion(Model model, @RequestParam("removeOption") Integer optionId, final QuestionAndOptions questionAndOptions, HttpServletRequest request) {
//        LOGGER.info("QuestionController: revomeOptionToQuestion [START]");
//        List<String> options = questionAndOptions.getOptions();
//        options.remove(options.get(optionId));
//        System.out.println("EGORKA-POMIDORKA");
//        System.out.println(optionId);
//        System.out.println(options);
//        questionAndOptions.setOptions(options);
//        model.addAttribute("questionAndOption", questionAndOptions);
//        LOGGER.info("QuestionController: revomeOptionToQuestion [FINISH]");
//        return "add_question";
//    }

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
