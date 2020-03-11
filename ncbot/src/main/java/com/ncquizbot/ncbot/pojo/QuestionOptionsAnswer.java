package com.ncquizbot.ncbot.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class QuestionOptionsAnswer {
    private Integer id;
    private String content;
    private List<Option> options;
    private String answer;
    private Integer weight;

    public QuestionOptionsAnswer() {
    }

    public QuestionOptionsAnswer(Integer id, String content, List<Option> options, String answer, Integer weight) {
        this.id = id;
        this.content = content;
        this.options = options;
        this.answer = answer;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
