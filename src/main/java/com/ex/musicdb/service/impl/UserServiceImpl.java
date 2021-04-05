package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.entities.UserRoleEntity;
import com.ex.musicdb.model.entities.enums.UserRole;
import com.ex.musicdb.model.servise.UserRegistrationServiceModel;
import com.ex.musicdb.model.servise.UserServiceModel;
import com.ex.musicdb.repository.UserRepository;
import com.ex.musicdb.repository.UserRoleRepository;
import com.ex.musicdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MusicDBUserService musicDbUserService;


    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, MusicDBUserService musicDbUserService) {

        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.musicDbUserService = musicDbUserService;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0){

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity().setUsername("admin").setPassword(passwordEncoder.encode("12345")).setFullname("Admin Adminov");
            UserEntity user = new UserEntity().setUsername("user").setPassword(passwordEncoder.encode("12345")).setFullname("Bai Ivan");

            admin.setRoles(List.of(adminRole, userRole));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel serviceModel) {

        UserEntity newUser = modelMapper.map(serviceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));


       UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER)
       .orElseThrow(() -> new IllegalStateException("USER role not found. Please seed the roles"));


       newUser.addRole(userRole);

      newUser =  userRepository.save(newUser);

      UserDetails principal = musicDbUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(

                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity findByName(String username) {

        return this.userRepository.findByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public UserServiceModel findById(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> {

                    return modelMapper
                            .map(userEntity, UserServiceModel.class);
                })
                .orElseThrow(IllegalArgumentException::new);
    }

}
