package com.jxw.icharity.enums;

import com.jxw.icharity.domain.Finance;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public enum FinanceEnum {

    PEOPLE_CONTRIBUTE(1,"公众募捐"),
    BUSINESS_CONTRIBUTE(2,"企业募捐"),
    GOVERN_CONTRIBUTE(3,"政府拨款"),
    PUBLIC_WELFARE(4,"社会福利"),
    VOLUNTEER_CONTRIBUTE(5,"志愿者投入"),
    MATERIAL_CONTRIBUTE(6,"物资捐助"),
    DISASTER_CONTRIBUTE(7,"赈灾捐助"),
    STAFF_SALARY(1,"人员工资"),

    ;
    Integer code;

    String desc;

    FinanceEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static void searchFinance(Integer code){
        boolean tag=false;
        for(FinanceEnum bean:FinanceEnum.values()){
            if(code==bean.getCode()){
                tag=true;
                break;
            }
        }
        Assert.isTrue(tag,"不存在对应的流水类型");
    }

}
