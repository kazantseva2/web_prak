package ru.msu.cmc.webprak.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "response")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Response implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vacancy")
    @ToString.Exclude
    @NonNull
    private Vacancy idVacancy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_seeker")
    @ToString.Exclude
    @NonNull
    private JobSeeker idSeeker;
}