package classes;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Complaints")
public class ComplaintsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int student;

    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintsEntity that = (ComplaintsEntity) o;

        if (id != that.id) return false;
        if (student != that.student) return false;
        if (message != null ? !Objects.equals(message,that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + student;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
