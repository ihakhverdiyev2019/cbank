package az.elixir.project.cbank.controllers;


import az.elixir.project.cbank.dto.UserLoginRequest;
import az.elixir.project.cbank.dto.UserRegisterRequest;
import az.elixir.project.cbank.models.TransactionModel;
import az.elixir.project.cbank.models.UserLoginTokenModel;
import az.elixir.project.cbank.models.UserModel;
import az.elixir.project.cbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest){
            userService.registerUser(userRegisterRequest);

        return new ResponseEntity("DONE", HttpStatus.OK);
    }

    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    public ResponseEntity<UserModel> getCustomerDataByCustomerCode(@RequestHeader("token") String token){
        return new ResponseEntity(userService.getCustomerDataByCustomerCode(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-budget", method = RequestMethod.GET)
    public ResponseEntity<UserModel> getBudgetByUser(@RequestHeader("token") String token){
        return new ResponseEntity(userService.getBudgetDetailsByCustomerCode(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-transactions", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionModel>> getTransactionListByUser(@RequestHeader("token") String token){
        return new ResponseEntity(userService.getTransactionListByCustomerCode(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/check-token", method = RequestMethod.GET)
    public ResponseEntity<UserLoginTokenModel> checkToken(@RequestHeader("token") String token){
        return new ResponseEntity(userService.checkTokenStatus(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginTokenModel> login(@RequestBody UserLoginRequest userLoginRequest){
        return new ResponseEntity(userService.login(userLoginRequest.getEmail(),userLoginRequest.getPassword()), HttpStatus.OK);
    }
}
