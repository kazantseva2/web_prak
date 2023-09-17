package ru.msu.cmc.webprak.models;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "prev_job")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class PrevJob implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_seeker", nullable = false)
    @ToString.Exclude
    @NonNull
    private JobSeeker idSeeker;

    @Column(name = "company")
    private String company;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private Integer salary;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrevJob other = (PrevJob) o;
        return Objects.equals(id, other.id)
                && Objects.equals(idSeeker, other.idSeeker)
                && company.equals(other.company)
                && position.equals(other.position)
                && Objects.equals(salary, other.salary);
    }
}
