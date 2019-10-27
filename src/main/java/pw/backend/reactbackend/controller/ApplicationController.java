package pw.backend.reactbackend.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.service.UserService;


@RestController
    @RequestMapping("/backend")
    public class ApplicationController {

        @Resource
        UserService userService;

        @GetMapping(value = "/UsersList")
        public List<User> getUsers() {
            return userService.findAll();

        }

        @PostMapping(value = "/createUser")
        public void createEmployee(@RequestBody User us) {
            userService.insertEmployee(us);

        }
}
