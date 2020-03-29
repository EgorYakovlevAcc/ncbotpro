package com.ncquizbot.ncbot.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class QuestionAndOptions {
    private String content;
    private List<Option> options;
    private Integer correctAnswerNum;
    private int activeNum;
}
