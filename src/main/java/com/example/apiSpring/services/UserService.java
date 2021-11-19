package com.example.apiSpring.services;

import java.util.ArrayList;

import com.example.apiSpring.models.UserModel;
import com.example.apiSpring.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }
    public UserModel createUser(UserModel user){
        return userRepository.save(user);
    }
    public boolean deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception errorMessage){
            return false;
        }
    }

}
