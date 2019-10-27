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
            return "Successed";
        }
        return "Failed: 409";

    }
    @GetMapping("/retrievebylogin/{login}")
    public String retrieveByLogin(@PathVariable String login) {
        User user = repository.findByLogin(login);
        if(user == null)
            return "Failed: 404";
        return user.ToString();
    }

    @PutMapping("/updatebylogin/{login}")
    public String update(@PathVariable String login,@RequestBody User newUser) {
        User user=repository.findByLogin(login);
        if(user==null) return "Failed: 404";
        user.setLogin(newUser.getLogin());
        user.setFirstname(newUser.getFirstname());
        user.setLastname(newUser.getLastname());
        user.setBirth(newUser.getBirth());
        user.setActive(newUser.getActive());
        repository.save(user);
        return "Successed";
    }

    @DeleteMapping("/deletebylogin/{login}")
    public String deleteByLogin(@PathVariable String login) {
        User user = repository.findByLogin(login);
        if (user == null) return "Failed: 404";
        repository.delete(user);
        return "Successed";
    }
}
