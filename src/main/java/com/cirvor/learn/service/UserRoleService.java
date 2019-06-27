package com.cirvor.learn.service;

import com.cirvor.learn.pojo.UserRole;
import java.util.List;

public interface UserRoleService {
    List<UserRole> listByUserId(int id);
}
