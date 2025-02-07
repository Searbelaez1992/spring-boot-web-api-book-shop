package com.example.BookStore.service;


import com.example.BookStore.models.Users;
import com.example.BookStore.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    public Users save(Users users){

        if(users.getId() !=null )
            users.setId(null);
        return usersRepository.save(users);

    }

    public Users findUsersById(Long userId) {

        return usersRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user id "+userId));

    }

    public Users updateUsers(Long id,  Users users){
        usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user id "+id));
        users.setId(id);

        return usersRepository.save(users);
    }

    public void deleteUsers( Long id){
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user id "+id));

        usersRepository.delete(users);
    }
}
