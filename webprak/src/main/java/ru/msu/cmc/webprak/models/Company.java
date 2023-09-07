package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "company")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Company implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false,  length=100)
    @NonNull
    private String login;

    @Column(name = "password", nullable = false, length=100)
    @NonNull
    private String password;

    @Column(name="name", length=200)
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "end_registration")
    private LocalDateTime endRegistration;

    @Column(name = "count_vacancies")
    private Integer countVacancies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company other = (Company) o;
        return Objects.equals(id, other.id)
                && login.equals(other.login)
                && password.equals(other.password)
                && name.equals(other.name)
                && info.equals(other.info)
                && Objects.equals(endRegistration, other.endRegistration)
                && Objects.equals(countVacancies, other.countVacancies);
    }
}
