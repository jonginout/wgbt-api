package com.board.controller.user;

import com.board.repository.domain.User;
import com.board.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody User user) throws Exception {

        System.out.println(user.toString());

        Map<String, Object> map = new HashMap<>();
        map.put("success", false);

        User loginUser = userService.loginUser(user.getId());
        if(loginUser != null){
            map.put("success", true);
            map.put("result", loginUser);
        }

        return map;
    }

}
