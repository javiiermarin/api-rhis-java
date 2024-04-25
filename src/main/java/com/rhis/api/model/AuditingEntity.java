package com.rhis.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public class AuditingEntity {
/*
    @CreatedBy
    @Column(name = "created_by")
    private String createBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createAt;

    @LastModifiedBy
    @Column(name = "update_by")
    private String updateBy;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

 */
}
