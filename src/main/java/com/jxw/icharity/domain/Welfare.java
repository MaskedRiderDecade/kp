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
@Table(name = "welfare")
public class Welfare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "edu_amount")
    private Integer eduAmount;

    @Column(name = "med_amount")
    private Integer medAmount;

    @Column(name = "employ_amount")
    private Integer employAmount;

    @Column(name = "house_amount")
    private Integer houseAmount;

    @Column(name = "weak_amount")
    private Integer trafficAmount;

    @OneToOne
    @JoinColumn(name="project_id")
    private Project project;

}
