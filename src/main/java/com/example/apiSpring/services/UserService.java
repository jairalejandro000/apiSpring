package com.example.apiSpring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.apiSpring.models.MailModel;
import com.example.apiSpring.models.UserModel;
import com.example.apiSpring.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails userDetails = new User(user.getUsername(), encoder.encode(user.getPassword()), roles);
        return userDetails;
    }


    public ArrayList<UserModel> getUsers(){
        try{
            return (ArrayList<UserModel>) userRepository.findAll();
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public Optional<UserModel> getUser(long id){
        try{
            return userRepository.findById(id);
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public UserModel createUser(UserModel user){
        try{
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public Optional<UserModel> deleteUser(long id){
        try{
            userRepository.deleteById(id);
            return (userRepository.findById(id));
        }catch (Exception errorMessage){
            System.out.println(errorMessage);
            return null;
        }
    }
    public UserModel updateUser(UserModel userUpdate, long id){
        Optional<UserModel> user = userRepository.findById(id);
        user.get().setUsername(userUpdate.getUsername());
        user.get().setEmail(userUpdate.getEmail());
        user.get().getPassword();
        userRepository.save(user.get());
        return userUpdate;

    }
    private JavaMailSender javaMailSender;
    public MailModel sendEmail(MailModel mail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mail.getFrom());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getText());
        simpleMailMessage.setTo(mail.getTo());
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
        return mail;
    }

    public String pruebaEncode(){
        String textEncode = (encoder.encode("fdsgksjbfkj"));
        return textEncode;
    }
}
