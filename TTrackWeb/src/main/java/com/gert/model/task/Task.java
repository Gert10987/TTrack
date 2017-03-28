package com.gert.model.task;

import com.gert.model.employer.Employer;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by gert on 03.03.17.
 */
@Entity
@Table(name="Task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Integer id;

    @NotEmpty
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotEmpty
    @Column(name = "START_DATE", nullable = false)
    private String startDate;

    @NotEmpty
    @Column(name = "END_DATE", nullable = false)
    private String endDate;

    @NotEmpty
    @Column(name = "EMAIL_OF_CLIENT", nullable = false)
    private String emailOfClient;

    @NotEmpty
    @Column(name = "PHONE_OF_CLIENT", nullable = false)
    private String phoneOfClient;

    @NotEmpty
    @Column(name = "START_OF_THE_ROUT", nullable = false)
    private String startOfTheRout;

    @NotEmpty
    @Column(name = "END_OF_THE_ROUT", nullable = false)
    private String endOfTheRout;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="EMPLOYER_ID", nullable = false)
    private Employer employer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEmailOfClient() {
        return emailOfClient;
    }

    public void setEmailOfClient(String emailOfClient) {
        this.emailOfClient = emailOfClient;
    }

    public String getPhoneOfClient() {
        return phoneOfClient;
    }

    public void setPhoneOfClient(String phoneOfClient) {
        this.phoneOfClient = phoneOfClient;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Task))
            return false;
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employer [id=" + id + ", name=" + name
                + ", startDate=" + startDate + ", endDate=" + endDate
                + ", emailOfClient=" + emailOfClient + "]";
    }

    public String getStartOfTheRout() {
        return startOfTheRout;
    }

    public void setStartOfTheRout(String startOfTheRout) {
        this.startOfTheRout = startOfTheRout;
    }

    public String getEndOfTheRout() {
        return endOfTheRout;
    }

    public void setEndOfTheRout(String endOfTheRout) {
        this.endOfTheRout = endOfTheRout;
    }
}

