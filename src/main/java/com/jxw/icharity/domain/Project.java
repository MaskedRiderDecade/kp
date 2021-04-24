package com.jxw.icharity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer amount;

    private String region;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

    private Integer type;

    //表的多对多关系
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name="manager",joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="staff_id"))
    private Set<Staff> staff;
}
