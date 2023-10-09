package com.bank_app.repositories;

import com.bank_app.dto.ContactDto;
import com.bank_app.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByUserId(Integer userId);
}
