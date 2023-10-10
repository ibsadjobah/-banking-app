package com.bank_app.services.impl;

import com.bank_app.config.JwtUtils;
import com.bank_app.dto.AccountDto;
import com.bank_app.dto.AuthenticationRequest;
import com.bank_app.dto.AuthenticationResponse;
import com.bank_app.dto.UserDto;
import com.bank_app.models.Role;
import com.bank_app.models.User;
import com.bank_app.repositories.RoleRepository;
import com.bank_app.repositories.UserRepository;
import com.bank_app.services.AccountService;
import com.bank_app.services.UserService;
import com.bank_app.validator.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "Role_User";

    private final UserRepository repository;

    private final AccountService accountService;

    private final ObjectsValidator<UserDto> validator;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authManager;

    private final RoleRepository roleRepository;

    @Override
    public Integer Save(UserDto dto) {

        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user).getId();
        // User saveUser = repository.save(user);
       // return saveUser.getId();

    }

    @Override
    public List<UserDto> findAll() {

        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {

        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {

        User user  = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user Was found user account validation"));

        // create bank account
       AccountDto accountDto = AccountDto.builder()
               .user(UserDto.fromEntity(user))
               .build();
       accountService.Save(accountDto);

       //apres on active le compte
        user.setActive(true);
       repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {

        User user  = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user Was found user account validation"));

        user.setActive(false);
        repository.save(user);

        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {

        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(
                findOrCreateRole(ROLE_USER)
        );

        User savedUser = repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullname", savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser);

       return AuthenticationResponse.builder()
               .token(token)
               .build();

    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())

        );
        final User user = repository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullname", user.getFirstname() + " " + user.getLastname());
        final String token = jwtUtils.generateToken(user, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null){
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }
}
