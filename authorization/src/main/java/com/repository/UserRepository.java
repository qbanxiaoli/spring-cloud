package com.repository;

import com.entity.User;
import com.dao.repository.BaseRepository;

/**
 * @author qbanxiaoli
 * @description
 * @create 2018/12/11 20:55
 */
public interface UserRepository extends BaseRepository<User, Long> {

    User findByUsername(String username);

    User findByPhone(String phone);

    User findByEmail(String email);

}
