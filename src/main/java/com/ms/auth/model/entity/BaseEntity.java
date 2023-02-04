package com.ms.auth.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(nullable = false, updatable = false, name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(nullable = false, name = "date_updated")
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @Version
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer version;
}
