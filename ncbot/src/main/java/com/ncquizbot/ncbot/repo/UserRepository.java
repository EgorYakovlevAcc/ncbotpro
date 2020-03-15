package com.ncquizbot.ncbot.repo;

import com.ncquizbot.ncbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByChatId(Long chatId);
    User findUserByTelegramId(Integer telegramId);
    List<User> findUsersByScoreAndIsPresentGiven(Integer score, boolean isPresentGiven);
    List<User> findUsersByIsPresentGiven(boolean isPresentGiven);
}
