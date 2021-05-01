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
@Table(name = "salary")
@DynamicInsert
@DynamicUpdate
public class Salary implements Serializable {
    private static final long serialVersionUID = 5135795759213711537L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="create_time")
    private Date createTime;

    private Integer amount;

    @OneToOne
    @JoinColumn(name="staff_id",referencedColumnName = "id")
    private Staff staff;

    private Date ctime;

    private Date mtime;

}
