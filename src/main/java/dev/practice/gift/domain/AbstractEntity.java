package dev.practice.gift.domain;

import java.time.ZonedDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

	@CreationTimestamp
	private ZonedDateTime createdAt;

	@CreationTimestamp
	private ZonedDateTime updatedAt;
}
