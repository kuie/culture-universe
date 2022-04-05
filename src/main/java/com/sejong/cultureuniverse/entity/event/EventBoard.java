package com.sejong.cultureuniverse.entity.event;

import static javax.persistence.FetchType.LAZY;

import com.sejong.cultureuniverse.entity.admin.Admin;
import com.sejong.cultureuniverse.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "event_seq", sequenceName = "event_seq", initialValue = 1, allocationSize = 1)
public class EventBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    private Long eventIdx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="admin_id")
    private Admin adminId;

    private String eventTitle;
    private String eventContent;
    private Long readCount;


    public void changeTitle(String eventTitle) { this.eventTitle = eventTitle;    }
    public void changeContent(String eventContent) {
        this.eventContent = eventContent;
    }

}
