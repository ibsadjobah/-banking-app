package com.bank_app.services;

import com.bank_app.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>  {

    List<ContactDto> findAllByUserId(Integer userId);
}
