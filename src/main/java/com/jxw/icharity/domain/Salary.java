package com.jxw.icharity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="create_time")
    private Date createTime;

    private Integer amount;

    @OneToOne
    @JoinColumn(name="staff_id",referencedColumnName = "id")
    private Staff staff;

}
