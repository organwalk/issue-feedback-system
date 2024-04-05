package com.issue.issuefeedbacksystem.bo;

import lombok.Data;

@Data
public class IssueCategoryBO {
    private Integer typeId;
    private String typeName;
    private DeptBO dept;

}
