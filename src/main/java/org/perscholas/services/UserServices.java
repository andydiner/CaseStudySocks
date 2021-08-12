package org.perscholas.services;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.IUserRepo;
import org.perscholas.exceptions.UserNotFoundException;
import org.perscholas.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServices {
    IUserRepo userRepo;
    @Autowired
    public UserServices(IUserRepo userRepo){
        this.userRepo = userRepo;
    }
    public User save(User u){
        return userRepo.save(u);
    }
    public User getUserByEmail(String email){
        User user = userRepo.getById(email);
        log.warn(String.valueOf(user));
        return user;
    }
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        log.warn(String.valueOf(users));
        return users;
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public boolean validateUser(User user) {
        User newUser = this.getUserByEmail(user.getEmailAddress());
        log.warn("user Validation in progress.");
        if (newUser == null) {
            return false;
        }

        String uPassword = newUser.getPassword();
        log.warn(uPassword);
        log.warn(newUser.getPassword());
        log.warn(user.getPassword());


        if (uPassword.equals(user.getPassword())) {
            log.warn("user password is correct.");

            return true;
        }
        else {
            log.warn("password does not match.");
            return false;
        }
    }

    public User get(String email) throws UserNotFoundException {
        Optional<User> getUser = userRepo.findById(email);
        if(getUser.isPresent()){
            return getUser.get();
        }
        throw new UserNotFoundException("Could not find any users with email: " + email);

    }

}