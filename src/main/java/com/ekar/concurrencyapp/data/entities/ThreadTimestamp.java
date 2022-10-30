package com.ekar.concurrencyapp.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="thread_timestamp")
public class ThreadTimestamp implements Serializable {

    private static final long serialVersionUID = 1035287170126492757L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "counter_value")
    private String counterValue;

    @Column(name = "request_timestamp")
    private Date requestTimestamp;
}
