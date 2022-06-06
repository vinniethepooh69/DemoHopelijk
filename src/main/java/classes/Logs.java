package classes;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Logs")
public class Logs {
    @Id
    @GeneratedValue
    private Long logID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime timeLog;

    public void setName(String nameOfLogin){
        name = nameOfLogin;
    }
    public void currentTime(){
        timeLog = LocalDateTime.now();
    }


}