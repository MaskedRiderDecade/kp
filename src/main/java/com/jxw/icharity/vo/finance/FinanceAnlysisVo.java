package com.jxw.icharity.vo.finance;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class FinanceAnlysisVo implements Serializable {
    private static final long serialVersionUID = 8430904399088920648L;

    List<FinanceVo>financeVos;

    List<FinanceTypeSumDto> sumDtos;

    @Data
    @Builder
    public static class FinanceTypeSumDto implements Serializable{
        private static final long serialVersionUID = -6253368876879169153L;

        private Integer type;

        private Integer sum;
    }
}
