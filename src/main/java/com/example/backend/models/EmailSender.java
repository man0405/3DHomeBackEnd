package com.example.backend.models;

public interface EmailSender {
    void send(String to, String email);
}
