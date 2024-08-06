package com.beije.account_service_be.business.accountancy.controller;

import com.beije.account_service_be.business.authentication.service.AuthenticationService;
import com.beije.account_service_be.system_global.domain.dto.BaseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/empl")
public class EmployeeController {

    private final AuthenticationService authenticationService;

    @ApiOperation(value="Gives access to the signed in employee's payrolls.")
    @GetMapping("/payment")
    public ResponseEntity<Object> getLoggedEmployeePayroll() {
        return ResponseEntity.ok(authenticationService.findCurrentUser());
    }

}
