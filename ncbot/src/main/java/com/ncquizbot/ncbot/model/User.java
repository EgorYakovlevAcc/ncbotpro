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
@AllArgsConstructor
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
    private Integer questionNumber;
    private boolean isGameOver;
    @OneToMany
    private List<QuestionAndUserAnswerNote> questionAndUserAnswerNoteList;
    public User() {
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(Integer currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isActiveNow() {
        return isActiveNow;
    }

    public void setActiveNow(boolean activeNow) {
        isActiveNow = activeNow;
    }

    public Date getLastSessionDate() {
        return lastSessionDate;
    }

    public void setLastSessionDate(Date lastSessionDate) {
        this.lastSessionDate = lastSessionDate;
    }

    public Date getEndSessionDate() {
        return endSessionDate;
    }

    public void setEndSessionDate(Date endSessionDate) {
        this.endSessionDate = endSessionDate;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public List<QuestionAndUserAnswerNote> getQuestionAndUserAnswerNoteList() {
        return questionAndUserAnswerNoteList;
    }

    public void setQuestionAndUserAnswerNoteList(List<QuestionAndUserAnswerNote> questionAndUserAnswerNoteList) {
        this.questionAndUserAnswerNoteList = questionAndUserAnswerNoteList;
    }

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
