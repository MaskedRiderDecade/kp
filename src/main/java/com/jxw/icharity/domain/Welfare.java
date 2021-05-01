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
@Table(name = "welfare")
@DynamicInsert
@DynamicUpdate
public class Welfare implements Serializable {
    private static final long serialVersionUID = -3989118638598099340L;
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

    private Date ctime;

    private Date mtime;

}
