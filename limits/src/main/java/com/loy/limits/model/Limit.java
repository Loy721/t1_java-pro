package com.loy.limits.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "limits")
@NoArgsConstructor
@Getter
@Setter
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "limits_seq")
    @SequenceGenerator(name = "limits_seq", sequenceName = "limits_seq", allocationSize = 10)
    private Long id;

    @Column(name = "limit_value")
    private long limit;

    @Column(name = "user_id", unique = true)
    private long userId;

    @Version
    private int version;

    public Limit(long limit, long userId) {
        this.limit = limit;
        this.userId = userId;
    }
}
