package com.tool.service.user;

import com.tool.dto.UserReq;
import com.tool.entity.Role;
import com.tool.entity.User;
import com.tool.repo.RoleRepo;
import com.tool.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    RoleRepo roleRepo;
    UserMapper userMapper;

    @Override
    public void createUser(UserReq userReq, String ip) {
        if (userRepo.findByUsername(userReq.getUsername()).isPresent())
            throw new RuntimeException("User exited");
        User user = userMapper.toUserFromUserReq(userReq);
        user.setIp(ip);

        HashSet<Role> roles = new HashSet<>();
        roleRepo.findById("USER").ifPresent(roles::add);
        user.setRoles(roles);

        userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
