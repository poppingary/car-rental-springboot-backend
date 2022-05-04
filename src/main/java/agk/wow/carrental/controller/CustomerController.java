package agk.wow.carrental.controller;

import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.model.CustomerDto;
import agk.wow.carrental.model.JwtRequest;
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
    public ResponseEntity register(@RequestBody CustomerDto customerDto) {
        return this.userService.register(customerDto, UserType.CUSTOMER);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody JwtRequest jwtRequest) throws Exception {
        return this.userService.login(jwtRequest);
    }
}