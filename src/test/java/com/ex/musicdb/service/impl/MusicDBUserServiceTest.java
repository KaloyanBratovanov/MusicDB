package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.entities.UserRoleEntity;
import com.ex.musicdb.model.entities.enums.UserRole;
import com.ex.musicdb.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class MusicDBUserServiceTest {

    private MusicDBUserService serviceToTest;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    void setUp(){
        serviceToTest = new MusicDBUserService(mockUserRepository);
    }

    @Test
    public void testUserNotFound(){
        Assertions.assertThrows(
                UsernameNotFoundException.class, () ->{

                    serviceToTest.loadUserByUsername("user_does_not_exist");
                }
        );
    }

    @Test
    void testExistingUser(){
        //prepare data
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername("bobo");
        userEntity.setPassword("123");

        UserRoleEntity roleUser = new UserRoleEntity();
        roleUser.setRole(UserRole.USER);
        UserRoleEntity roleAdmin = new UserRoleEntity();
        roleAdmin.setRole(UserRole.ADMIN);

        userEntity.setRoles(List.of(roleUser, roleAdmin));

        //configure mock
        Mockito.when(mockUserRepository.findByUsername("bobo")).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = serviceToTest.loadUserByUsername("bobo");

        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        List<String> authorities =userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toList());

        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
    }



}
