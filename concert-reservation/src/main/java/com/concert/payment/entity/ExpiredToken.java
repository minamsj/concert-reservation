package com.concert.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "token")
public class ExpiredToken {
    @Id
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "delete_yn")
    private boolean deleteYn;
}
