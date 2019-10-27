package pw.backend.reactbackend.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.repository.UserRepository;
import pw.backend.reactbackend.service.UserService;


@RestController
    @RequestMapping("/backend")
    public class ApplicationController {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService service;



    @GetMapping(value = "/findall")
    public List<User> findAll() {

        List<User> users = (ArrayList<User>) repository.findAll();
        return users;
    }

    @PostMapping(value = "/create")
    public String create(@RequestBody User newUser) {
        if (service.checkLogin(newUser.getLogin())==null) {
          repository.save(new User(newUser.getLogin(), newUser.getFirstname(), newUser.getLastname(), newUser.getBirth(), newUser.getActive()));
            return "Success";
        }
        return "Failed";

    }
    @GetMapping("/findbylogin/{login}")
    public String findByLogin(@PathVariable String login) {
        User user = repository.findByLogin(login);
        if(user == null)
            return "No user";
        return user.ToString();
    }

}
