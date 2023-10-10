package com.bank_app.services;

import com.bank_app.dto.AuthenticationRequest;
import com.bank_app.dto.AuthenticationResponse;
import com.bank_app.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

Integer validateAccount(Integer id);

Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
