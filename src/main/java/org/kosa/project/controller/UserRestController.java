package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/userEmailCheck")
    public ResponseEntity <Boolean> checkUserName(@RequestParam String email){
        String userEmail = userService.findUserByEmailCheck(email);

        boolean isEmailCheck = (userEmail==null);
        System.out.println(isEmailCheck +"  isEmailCheck");
        return ResponseEntity.ok(userEmail == null);
    }
}
