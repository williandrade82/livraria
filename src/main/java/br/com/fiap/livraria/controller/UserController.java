package br.com.fiap.livraria.controller;

import br.com.fiap.livraria.dto.AuthDTO;
import br.com.fiap.livraria.dto.JwtDTO;
import br.com.fiap.livraria.dto.UserCreateDTO;
import br.com.fiap.livraria.dto.UserDTO;
import br.com.fiap.livraria.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO){
        return userService.create(userCreateDTO);
    }

    @PostMapping("login")
    public JwtDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }
}
