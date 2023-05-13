package com.anilcan.urlshortener.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String longURL;

    @Column(nullable = false, unique = true)
    private String shortURL;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
