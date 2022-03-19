package dev.jt.community.controller;

import dev.jt.community.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(null);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(null);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> readUserAll(){
        return ResponseEntity.ok(null);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto
    ){
        return ResponseEntity.ok(null);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(null);
    }
}
