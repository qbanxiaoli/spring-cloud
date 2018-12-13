package com.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/13 17:23
 */
@RestController
@PreAuthorize("hasRole('ROLE_TCM_ADMIN')")
public class UserQueryController {

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
