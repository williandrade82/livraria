package br.com.fiap.livraria.service;

import br.com.fiap.livraria.dto.AuthDTO;
import br.com.fiap.livraria.dto.JwtDTO;
import br.com.fiap.livraria.dto.UserCreateDTO;
import br.com.fiap.livraria.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface UserService {
    UserDTO create(UserCreateDTO userCreateDTO);

    JwtDTO login(AuthDTO authDTO);
}
