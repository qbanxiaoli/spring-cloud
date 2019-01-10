package com.controller;

import com.enums.response.CommonResponseEnum;
import com.model.vo.JwtVO;
import com.model.vo.ResponseVO;
import com.bean.JwtResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/13 17:23
 */
@Slf4j
@RestController
@PreAuthorize("hasRole('TCM_ADMIN')")
public class UserQueryController {

    @GetMapping("/user")
    public ResponseVO jwt() {
        String userId = JwtResource.getUserId();
        log.info("用户id为：" + userId);
        JwtVO jwtVO = JwtResource.parseJwt();
        log.info("用户名为：" + jwtVO.getUsername());
        return new ResponseVO<>(CommonResponseEnum.SUCCESS, jwtVO);
    }

}
