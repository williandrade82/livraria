package br.com.fiap.livraria.service;

import br.com.fiap.livraria.dto.AuthDTO;
import br.com.fiap.livraria.dto.JwtDTO;
import br.com.fiap.livraria.dto.UserCreateDTO;
import br.com.fiap.livraria.dto.UserDTO;
import br.com.fiap.livraria.entity.User;
import br.com.fiap.livraria.repository.UserRepository;
import br.com.fiap.livraria.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        User savedUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setUsername(savedUser.getUsername());
        return userDTO;
    }

    @Override
    public JwtDTO login(AuthDTO authDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(),authDTO.getPassword())
            );

        } catch (AuthenticationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        String token = jwtTokenUtil.generateToken(authDTO.getUsername());
        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setToken(token);
        return jwtDTO;
    }
}
