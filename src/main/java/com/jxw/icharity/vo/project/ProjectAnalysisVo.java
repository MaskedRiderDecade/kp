package com.jxw.icharity.vo.project;

import com.jxw.icharity.vo.welfare.WelfareVo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectAnalysisVo {

    ProjectListVo projectListVo;

    List<WelfareVo>welfareVos;

}
