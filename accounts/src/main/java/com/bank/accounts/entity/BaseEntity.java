package com.bank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name = "Created_At", updatable = false)// column name needs to be mentioned separately only when it differs
    // from field name
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "Updated_At", insertable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "Created_By", updatable = false)
    private  String createdBy;

    @LastModifiedBy
    @Column(name = "Updated_By", insertable = false)
    private String updatedBy;
}
