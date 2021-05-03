package com.jxw.icharity.util;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimeRangeDate {

    private Date startTime;

    private Date endTime;

}
