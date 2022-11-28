package com.playground.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditAwareModel {
    private String createdBy;
    private LocalDateTime createdOn;
    private String updatedBy;
    private String updatedUsing;
    private LocalDateTime updatedOn;
}
