package com.jxw.icharity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region")
@DynamicInsert
@DynamicUpdate
public class Region implements Serializable {

    private static final long serialVersionUID = 6141283900408433979L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private String name;

    @Column(name = "is_invested")
    private Integer isInvested;

    private Integer amount;

    private Date ctime;

    private Date mtime;

}
