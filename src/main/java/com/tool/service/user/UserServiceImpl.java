package com.tool.service.user;

import com.tool.dto.UserCreateRequest;
import com.tool.entity.Role;
import com.tool.entity.User;
import com.tool.repo.RoleRepo;
import com.tool.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    RoleRepo roleRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreateRequest userCreateRequest, String ip) {
        if (userRepo.findByUsername(userCreateRequest.getUsername()).isPresent())
            throw new RuntimeException("User exited");
        User user = userMapper.toUserFromUserReq(userCreateRequest);
        user.setIp(ip);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepo.findById("USER").ifPresent(roles::add);
        user.setRoles(roles);

        return  userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
