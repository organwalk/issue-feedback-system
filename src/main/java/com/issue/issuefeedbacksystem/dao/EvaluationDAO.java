package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.dto.EvaluationDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluationDAO {
    @Insert("insert into evaluation (issue_id, rating, comment) values (#{issueId},#{rating},#{comment})")
    int insertEvaluation(EvaluationDTO evaluationDTO);
}
