package io.susimsek.filteringexample.domain.audit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractDateAuditingEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    Date lastModifiedDate;

}
