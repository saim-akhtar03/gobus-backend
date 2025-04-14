package com.tickets.GoBus.request;

import lombok.Data;

@Data
public class CreateBusRequsest {
    private String busNumber;
    private int capacity;
    private String busDriverName;
    private StringBuilder busDriverNumber;
    private double seatFair;
}
