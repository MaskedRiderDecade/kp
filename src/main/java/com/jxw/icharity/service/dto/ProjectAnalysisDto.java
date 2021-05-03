package com.jxw.icharity.service.dto;

import com.jxw.icharity.domain.Project;
import com.jxw.icharity.domain.Welfare;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectAnalysisDto {

    private Project project;

    private List<Welfare> welfares;

}
