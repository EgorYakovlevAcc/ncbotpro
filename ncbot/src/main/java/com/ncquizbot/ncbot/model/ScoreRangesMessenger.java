package com.ncquizbot.ncbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "score_ranges")
public class ScoreRangesMessenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer maxBorder;
    private Integer minBorder;
    @Lob
    private String text;
    @Lob
    private byte[] picture;
}
