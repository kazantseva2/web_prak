package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Vacancy implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company", nullable = false)
    @ToString.Exclude
    @NonNull
    private Company idCompany;

    @Column(name = "title", nullable = false,  length=200)
    @NonNull
    private String title;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "requirements")
    private String requirements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy other = (Vacancy) o;
        return Objects.equals(id, other.id)
                && Objects.equals(idCompany, other.idCompany)
                && title.equals(other.title)
                && position.equals(other.position)
                && Objects.equals(salary, other.salary)
                && requirements.equals(other.requirements);
    }
}
