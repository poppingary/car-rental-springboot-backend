package agk.wow.carrental.controller;

import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.rpcdomain.request.CustomerRegisterRequest;
import agk.wow.carrental.rpcdomain.request.JwtRequest;
import agk.wow.carrental.rpcdomain.request.UpdateCustomerCredentialRequest;
import agk.wow.carrental.rpcdomain.request.UpdateIndividualCustomerRequest;
import agk.wow.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody CustomerRegisterRequest customerRegisterRequest) {
        return this.userService.register(customerRegisterRequest, UserType.CUSTOMER);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody JwtRequest jwtRequest) throws Exception {
        return this.userService.login(jwtRequest);
    }

    @PostMapping(value = "/update/individual")
    public ResponseEntity updateIndividualCustomer(@RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        return this.userService.updateIndividualCustomer(updateIndividualCustomerRequest);
    }

    @PostMapping(value = "/update/credential")
    public ResponseEntity updateCredential(@RequestBody UpdateCustomerCredentialRequest updateCustomerCredentialRequest) {
        return this.userService.updateCustomerCredential(updateCustomerCredentialRequest);
    }
}