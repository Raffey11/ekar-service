package com.ekar.concurrencyapp.data.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="request_response_log")
public class RequestResponseLog implements Serializable {

    private static final long serialVersionUID = 1065687070161252756L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request")
    private String request;

    @Column(name = "request_timestamp")
    private Date requestTimestamp;
}
