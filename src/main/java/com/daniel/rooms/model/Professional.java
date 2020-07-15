package com.daniel.rooms.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "professional")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professionalid;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    private LocalTime beginat;

    @NotNull
    private LocalTime endat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "professional_dayofweek",
        joinColumns = @JoinColumn(name = "professional_id"),
        inverseJoinColumns = @JoinColumn(name = "dayoffweek_id"))
    private Set<DayOfWeek> dayofweekset = new HashSet<>();

    private boolean requiresSpecialtyRoom;

    public Professional() { }

    public Professional(String name, LocalTime beginAt, LocalTime endat, Set<DayOfWeek> dayofweekset, boolean requiresSpecialtyRoom) {
        this.name = name;
        this.beginat = beginAt;
        this.endat = endat;
        this.dayofweekset = dayofweekset;
        this.requiresSpecialtyRoom = requiresSpecialtyRoom;
    }

    public Long getProfessionalid() {
        return professionalid;
    }

    public void setProfessionalid(Long professionalid) {
        this.professionalid = professionalid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getBeginat() {
        return beginat;
    }

    public void setBeginat(LocalTime beginat) {
        this.beginat = beginat;
    }

    public LocalTime getEndat() {
        return endat;
    }

    public void setEndat(LocalTime endat) {
        this.endat = endat;
    }

    public Set<DayOfWeek> getDayofweekset() {
        return dayofweekset;
    }

    public void setDayofweekset(Set<DayOfWeek> dayOfWeekSet) {
        this.dayofweekset = dayOfWeekSet;
    }

    public boolean isRequiresSpecialtyRoom() {
        return requiresSpecialtyRoom;
    }

    public void setRequiresSpecialtyRoom(boolean requiresSpecialtyRoom) {
        this.requiresSpecialtyRoom = requiresSpecialtyRoom;
    }

    @Override
    public String toString() {
        return "Professional{" +
                "professionalid=" + professionalid +
                ", name='" + name + '\'' +
                ", beginat=" + beginat +
                ", endat=" + endat +
                ", dayOfWeekSet=" + dayofweekset +
                ", requiresSpecialtyRoom=" + requiresSpecialtyRoom +
                '}';
    }
}