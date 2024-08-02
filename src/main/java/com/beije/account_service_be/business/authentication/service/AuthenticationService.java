package com.beije.account_service_be.business.authentication.service;

import com.beije.account_service_be.business.authentication.domain.SecurityUserAdapter;
import com.beije.account_service_be.business.authentication.domain.dto.request.UserRequestDto;
import com.beije.account_service_be.business.authentication.domain.dto.response.UserResponseDto;
import com.beije.account_service_be.business.authentication.domain.entity.UserEntity;
import com.beije.account_service_be.business.authentication.repository.UserRepository;
import com.beije.account_service_be.business.authentication.utils.AuthenticationMapper;
import com.beije.account_service_be.system_global.domain.dto.BaseDto;
import com.beije.account_service_be.system_global.exception.ErrorCodeExceptionEnum;
import com.beije.account_service_be.system_global.exception.GenericErrorCodeException;
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

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService implements UserDetailsService {

    private final AuthenticationMapper authenticationMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public BaseDto signUp(UserRequestDto userRequestDto) throws UserExistsException {


        return Optional.ofNullable(userRequestDto)
                .map(UserRequestDto::email)
                .flatMap(this::findUserByUsername)
                .map(userEntity -> {
                    var registeredUser = registerNewUser(userRequestDto);
                    logger.info("User registration success: {}", registeredUser);
                    return registeredUser;
                })
                .orElseThrow(() -> new GenericErrorCodeException(ErrorCodeExceptionEnum.ER003, "Empty Request"));
    }



    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByEmailCaseInsensitive(username);
    }

    private UserResponseDto registerNewUser(UserRequestDto userRequestDto) {

        return Optional.of(userRequestDto)
                .map(authenticationMapper::userRequestDtoToUserEntity)
                .map(
                        user -> {
                            user.setPassword(passwordEncoder.encode(user.getPassword()));
                            return userRepository.save(user);
                        }
                )
                .map(userRepository::save)
                .map(authenticationMapper::userEntityToUserResponseDto)
                .orElseThrow(() -> new IllegalArgumentException("Error during user registration"));

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmailCaseInsensitive(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email :%s not found or does not exist ", username)));
        return new SecurityUserAdapter(userEntity);
    }
}
