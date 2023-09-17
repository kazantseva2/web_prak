package ru.msu.cmc.webprak.models;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job_seeker")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class JobSeeker implements CommonEntity<Long> {

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

    @Column(name="full_name", length=200)
    private String fullName;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "education_info")
    private String educationInfo;

    @Column(name = "status")
    private String status;

    @Column(name = "desired_position")
    private String desiredPosition;

    @Column(name = "desired_salary")
    private Integer desiredSalary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobSeeker other = (JobSeeker) o;
        return Objects.equals(id, other.id)
                && login.equals(other.login)
                && password.equals(other.password)
                && fullName.equals(other.fullName)
                && contactInfo.equals(other.contactInfo)
                && educationInfo.equals(other.educationInfo)
                && status.equals(other.status)
                && desiredPosition.equals(other.desiredPosition)
                && Objects.equals(desiredSalary, other.desiredSalary);
    }
}
