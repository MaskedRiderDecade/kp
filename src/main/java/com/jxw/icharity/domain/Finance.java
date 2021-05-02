package com.jxw.icharity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "finance")
@DynamicInsert
@DynamicUpdate
public class Finance implements Serializable {
    private static final long serialVersionUID = -1799139169213226242L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amount;

    @OneToOne
    @JoinColumn(name="project_id")
    private Project project;

    @Column(name = "create_time")
    private Date createTime;

    private Integer type;

    @Column(name="is_positive")
    private Integer isPositive;

    private Date ctime;

    private Date mtime;

}
