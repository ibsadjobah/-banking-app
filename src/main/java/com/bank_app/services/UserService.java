package com.bank_app.services;

import com.bank_app.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

Integer validateAccount(Integer id);

Integer invalidateAccount(Integer id);

}
