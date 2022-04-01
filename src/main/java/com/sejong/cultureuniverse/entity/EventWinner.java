package com.sejong.cultureuniverse.entity;

import static javax.persistence.FetchType.LAZY;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "winner_seq", sequenceName = "winner_seq", initialValue = 1, allocationSize = 1)
public class EventWinner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "winner_seq")
    private Long winnerIdx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="adminId")
    private Admin adminId;

    private String winTitle;
    private String winContent;
    private Long readCount;
    private Date regDate;
    private Date modDate;

}
