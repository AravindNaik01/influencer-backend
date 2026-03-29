package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository repo;

    public String sendRequest(ConnectionRequest request) {
        request.setStatus("PENDING");
        repo.save(request);
        return "Request sent!";
    }

    public List<ConnectionRequest> getAllRequests() {
        return repo.findAll();
    }

    public String acceptRequest(Long id) {
        ConnectionRequest req = repo.findById(id).orElse(null);

        if (req == null) {
            return "Request not found";
        }

        req.setStatus("ACCEPTED");
        repo.save(req);

        return "Request accepted!";
    }
}