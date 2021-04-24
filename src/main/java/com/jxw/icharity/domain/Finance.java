package com.jxw.icharity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "finance")
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amount;

    @OneToOne
    @JoinColumn(name="project_id")
    private Project project;

    @Column(name = "create_time")
    private Integer createTime;

    private Integer type;

    @Column(name="is_positive")
    private Integer isPositive;

}
