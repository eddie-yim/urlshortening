package com.example.domain.url;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_seq")
    @SequenceGenerator(name = "url_seq", initialValue = 100_000, allocationSize = 1)
    protected Long id;

    @NonNull
    @Column(unique = true, nullable = false, length = 1024)
    protected String original;

    @Column(nullable = false)
    protected Long count = 0L;

    @Column
    @CreatedDate
    protected LocalDateTime created;

    @PrePersist
    public void setCreated() {
        if (this.created == null) {
            this.created = LocalDateTime.now();
        }
    }

    public void increaseCount() {
        this.count++;
    }
}
