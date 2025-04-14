package com.tickets.GoBus.request;

import lombok.Data;

@Data
public class SearchRoutesRequest {
    String source;
    String destination;
}
