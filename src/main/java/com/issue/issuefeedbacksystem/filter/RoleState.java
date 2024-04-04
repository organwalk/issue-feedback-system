package com.issue.issuefeedbacksystem.filter;

import org.springframework.stereotype.Component;

@Component
public class RoleState {
    public RoleName getRole(Integer roleId){
        if (roleId == 1){
            return RoleName.STUDENT;
        }else if (roleId == 2){
            return RoleName.TEACHER;
        }else if (roleId == 3){
            return RoleName.DEPT_LEADER;
        }else if (roleId == 4){
            return RoleName.SCHOOL_LEADER;
        }else if (roleId == 5){
            return RoleName.ADMIN;
        }
        return null;
    }
}
