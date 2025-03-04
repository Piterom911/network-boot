package org.telran.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telran.social.dto.NetworkUserResponseDto;
import org.telran.social.model.NetworkUser;
import org.telran.social.service.NetworkUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class NetworkUserController {

    @Autowired
    private NetworkUserService userService;

    @GetMapping
    public List<NetworkUserResponseDto> getAll() {
        return userService.getAll().stream().map(user -> NetworkUserResponseDto.builder()
                        .name(user.getName())
                        .id(user.getId())
                        .surname(user.getSurname())
                        .build())
                .toList();
    }

    @GetMapping("/{id}")
    public NetworkUser getById(@PathVariable(name = "id") Long userId) {
        return userService.getById(userId);
    }

    @GetMapping("/filter")
    public List<NetworkUser> filter(@RequestParam(name = "minAge", required = false) Integer minAge,
                                    @RequestParam(name = "maxAge", required = false) Integer maxAge) {
        return userService.filter(minAge, maxAge);
    }

    @PostMapping
    public NetworkUser create(@RequestBody NetworkUser networkUser) {
        return userService.create(networkUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }
}
