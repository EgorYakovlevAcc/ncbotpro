package com.ncquizbot.ncbot.config.timer;

import com.ncquizbot.ncbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Objects;

@Configuration
public class TimerConfigurationImpl implements TimerConfiguration {
    //10 minutes
    private static final Long TIME_GAP = 600000L;

    @Autowired
    private UserService userService;
    @Override
    @Scheduled(fixedRate = 60000)
    public void sheduleUsersSessions() {
        userService.findAll().stream().forEach(user -> {
            Date currentDate = new Date();
            Date date = user.getLastSessionDate();
            if (Objects.nonNull(date)){
                if (checkIsTimeCameOut(currentDate, date, TIME_GAP)) {
                    userService.delete(user);
                }
            }
        });
    }

    private boolean checkIsTimeCameOut(Date d1, Date d2, Long gap){
        Long delta = d1.getTime() - d2.getTime();
        return delta > gap;
    }
}
