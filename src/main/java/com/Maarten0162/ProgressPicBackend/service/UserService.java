package com.Maarten0162.ProgressPicBackend.service;

import org.springframework.stereotype.Service;

import com.Maarten0162.ProgressPicBackend.DAL.UserRepo;
import com.Maarten0162.ProgressPicBackend.model.User;

@Service
public class UserService {
    
    private final UserRepo repo;

    public UserService(UserRepo userRepo) {
        this.repo = userRepo;
    }

    public User createUser(User NewUser) throws Exception{

        if (NewUser.getUuid() != null) {
            throw new Exception("User Already Has A UUID, Does It Already Exist?");
        } else {
        return repo.save(NewUser);
        }
    }
}
