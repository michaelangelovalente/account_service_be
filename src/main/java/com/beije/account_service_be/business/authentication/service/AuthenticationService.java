package com.beije.account_service_be.business.authentication.service;

import com.beije.account_service_be.business.authentication.domain.SecurityUserAdapter;
import com.beije.account_service_be.business.authentication.domain.dto.request.UserRequestDto;
import com.beije.account_service_be.business.authentication.domain.entity.UserEntity;
import com.beije.account_service_be.business.authentication.repository.UserRepository;
import com.beije.account_service_be.business.authentication.utils.AuthenticationMapper;
import com.beije.account_service_be.system_global.domain.dto.BaseDto;
import com.beije.account_service_be.system_global.exception.UserExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService implements UserDetailsService {

    private final AuthenticationMapper authenticationMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public BaseDto signup(UserRequestDto userRequestDto) {
        logger.info("User registration request: {}", userRequestDto);
        if(Objects.isNull(userRequestDto)){
            return null; //TODO:
        }

        Optional<UserEntity> userEntity = userRepository.findByEmail(userRequestDto.email());
        if( userEntity.isPresent()){
            throw  new UserExistsException();
        }

        var validUserEntity = authenticationMapper.userRequestDtoToUserEntity(userRequestDto);
        validUserEntity.setPassword(passwordEncoder.encode(validUserEntity.getPassword()));
        var registeredUser = authenticationMapper.userEntityToUserResponseDto(
                userRepository.save(validUserEntity)
        );


        logger.info("User registration success: {}", registeredUser);

        return  registeredUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow( () -> new UsernameNotFoundException(String.format("User with email :%s not found or does not exist ", username)));
        return new SecurityUserAdapter(userEntity);
    }
}
