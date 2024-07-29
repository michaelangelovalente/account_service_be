package com.beije.account_service_be.business.authentication.controller;

import com.beije.account_service_be.business.authentication.domain.dto.request.UserRequestDto;
import com.beije.account_service_be.business.authentication.service.AuthenticationService;
import com.beije.account_service_be.business.system_global.domain.dto.BaseDto;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    //POST api/auth/signup allows the user to register on the service;
    @ApiOperation(value="Allows the user to register on the service")
    @PostMapping("/signup")
    public ResponseEntity<BaseDto> signup(@Valid @RequestBody UserRequestDto userRequestDto){

        return ResponseEntity.ok(authenticationService.signup(userRequestDto));
    }


}
