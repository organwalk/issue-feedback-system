package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.bo.PendingUserBO;
import com.issue.issuefeedbacksystem.dao.UserDAO;
import com.issue.issuefeedbacksystem.dto.UserLoginDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.entity.User;
import com.issue.issuefeedbacksystem.service.UserService;
import com.issue.issuefeedbacksystem.utils.JwtUtil;
import com.issue.issuefeedbacksystem.vo.CommonResult;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final JwtUtil jwtUtil;
    @Override
    public MsgResult register(UserRegistrationDTO userRegistrationDTO) {
        int row = userDAO.insertUser(userRegistrationDTO);
        return row > 0 ? MsgResult.success("注册成功") : MsgResult.fail("注册失败");
    }

    @Override
    public CommonResult<?> login(UserLoginDTO userLoginDTO) {
        User user = userDAO.selectUserByPhone(userLoginDTO.getPhone());
        if (Objects.isNull(user.getRoleId())){
            return CommonResult.fail("当前账号尚未分配角色，请等待管理员处理");
        }
        if (Objects.equals(userLoginDTO.getPassword(), user.getPasswordHash())){
            String token = jwtUtil.generateToken(user.getUserId(), user.getRoleId());
            return CommonResult.success("登录成功", token);
        }
        return CommonResult.fail("手机号码或密码错误");
    }

    @Override
    public PagedResult<?> getPendingUserList(Integer size, Integer offset) {
        Integer total = userDAO.countPendingUserSum();
        if (total == 0) return PagedResult.fail("待处理用户列表为空");
        List<PendingUserBO> list = userDAO.selectPendingUserList(size, offset);
        return PagedResult.success("已成功获取待处理用户列表", total, list);
    }
}
