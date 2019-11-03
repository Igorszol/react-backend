package pw.backend.reactbackend.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.repository.UserRepository;
import pw.backend.reactbackend.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
    @RequestMapping("/users")
    public class ApplicationController {

    @Autowired
    UserRepository repository;

    @Autowired
    UserService service;



    @GetMapping(path = "")
    public List<User> findAll() {
        List<User> users = (ArrayList<User>) repository.findAll();
        return users;
    }

    @PostMapping(path = "")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok().body(repository.save(user));
    }
    @GetMapping(path = "/{login}")
    public ResponseEntity<User> findByLogin(@PathVariable String login) {
        return new ResponseEntity<User>(service.FindByLogin(login), HttpStatus.OK);
    }


    @PatchMapping(path = "")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok().body(service.updateUser(updatedUser));
    }

    @DeleteMapping("/{login}")
    public String deleteByLogin(@PathVariable String login) {
        User user = service.FindByLogin(login);
        repository.delete(user);
        return "User deleted.";
    }
}
