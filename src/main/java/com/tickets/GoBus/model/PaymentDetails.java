package com.tickets.GoBus.model;

import com.tickets.GoBus.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentId;
    private String razorPaymentLinkId;
    private String razorPaymentLinkReferenceId;
    private String razorPaymentLinkStatus;
    private String razorPaymentLinkIdZWSP;
    private PaymentStatus paymentStatus;
}
