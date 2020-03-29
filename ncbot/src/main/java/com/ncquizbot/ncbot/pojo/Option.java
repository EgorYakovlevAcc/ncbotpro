package com.ncquizbot.ncbot.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Option {
    private String content;
    private String reaction;

    public Option(String content) {
        this.content = content;
    }
}
