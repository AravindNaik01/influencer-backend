package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class ConnectionController {

    private final ConnectionService service;

    public ConnectionController(ConnectionService service) {
        this.service = service;
    }

    @PostMapping("/request")
    public String sendRequest(@RequestBody ConnectionRequest request) {
        return service.sendRequest(request);
    }

    @GetMapping("/requests")
    public List<ConnectionRequest> getAllRequests() {
        return service.getAllRequests();
    }

    @PutMapping("/accept/{id}")
    public String acceptRequest(@PathVariable("id") Long id) {
        return service.acceptRequest(id);
    }
}