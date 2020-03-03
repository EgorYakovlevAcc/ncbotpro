package com.ncquizbot.ncbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAndUserAnswerNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Question question;
    @Column(name = "answer")
    private String userAnswer;
    @ManyToOne
    private User user;
}
