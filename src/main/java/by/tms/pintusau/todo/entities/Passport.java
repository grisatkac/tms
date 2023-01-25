package by.tms.pintusau.todo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SuperBuilder
@Entity
public class Passport extends AbstractEntity {

    private String serialNumber;
    @OneToOne(mappedBy = "passport")
    @JsonIgnore
    User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Passport passport = (Passport) o;
        return id != null && Objects.equals(id, passport.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
