package com.ex.musicdb.service;

import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.servise.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean userNameExists(String username);

    UserEntity findByName(String username);
}
