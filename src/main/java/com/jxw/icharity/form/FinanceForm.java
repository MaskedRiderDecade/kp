package com.jxw.icharity.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class FinanceForm implements Serializable {
    private static final long serialVersionUID = -108966233423094568L;

    private Integer amount;

    private Integer project_id;

    private String create_time;

    private Integer type;

}
