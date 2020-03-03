package com.ncquizbot.ncbot.repo;

import com.ncquizbot.ncbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByChatId(Long chatId);
    User findUserByTelegramId(Integer telegramId);
}
