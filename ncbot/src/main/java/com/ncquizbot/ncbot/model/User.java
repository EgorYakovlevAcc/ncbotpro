package com.ncquizbot.ncbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer telegramId;
    private Long chatId;
    private String firstName;
    private String lastName;
    private Integer currentQuestionId;
    private Integer score;
    private boolean isActiveNow;
    @Column(name = "start_session")
    private Date lastSessionDate;
    @Column(name = "end_session")
    private Date endSessionDate;
    @OneToMany
    private List<QuestionAndUserAnswerNote> questionAndUserAnswerNoteList;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", telegramId=" + telegramId +
                ", chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", currentQuestionId=" + currentQuestionId +
                ", score=" + score +
                ", isActiveNow=" + isActiveNow +
                '}';
    }
}
