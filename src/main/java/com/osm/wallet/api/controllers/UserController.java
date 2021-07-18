package com.osm.wallet.api.controllers;

import com.osm.wallet.api.domain.util.Response;
import com.osm.wallet.api.domain.util.User;
import com.osm.wallet.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> createNewUser(
            @RequestBody User user) {
        return new ResponseEntity<Response>(userService.createUser(user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getPhoneNumber(),
                user.getPassword(), user.getUsername()), HttpStatus.OK);
    }

    @RequestMapping(value= "/validateEmail", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE},
    produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response> validateEmail(
            @RequestBody User user) throws InstantiationException, IllegalAccessException {
        return new ResponseEntity<Response>(userService.validateEmail(user.getEmailAddress(), user.getToken()), HttpStatus.OK);
    }

}
