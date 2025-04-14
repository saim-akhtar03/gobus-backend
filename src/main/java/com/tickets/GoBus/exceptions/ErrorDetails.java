package com.tickets.GoBus.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {
    String error;
    String detals;
    LocalDateTime timeStamp;
}
