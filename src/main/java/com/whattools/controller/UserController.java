package com.whattools.controller;

import com.alibaba.fastjson.JSON;
import com.whattools.annotation.UserLoginToken;
import com.whattools.common.BaseResponse;
import com.whattools.common.ErrorCode;
import com.whattools.common.ResultUtils;
import com.whattools.exception.BusinessException;
import com.whattools.pojo.UserDao;
import com.whattools.pojo.requset.UserLoginRequest;
import com.whattools.pojo.requset.UserRegisterRequest;
import com.whattools.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "用户模块", description = "用户所有操作")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    @Operation(summary = "注册接口")
    public BaseResponse<UserDao> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 校验
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        UserDao result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "登录接口")
    public BaseResponse<Object> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        // 校验
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        Object result = userService.userLogin(userAccount, userPassword,request);
        return ResultUtils.success(result);
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    //登录注解，说明该接口必须登录获取token后，在请求头中加上token并通过验证才可以访问
    public String getMessage(){
        return "你已通过验证";
    }

}