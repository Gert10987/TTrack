package com.gert.model.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gert on 03.03.17.
 */
@Entity
@Table(name="PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable {

    @Id
    private String series;

    @Column(name="USERNAME", unique=true, nullable=false)
    private String username;

    @Column(name="TOKEN", unique=true, nullable=false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_used;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }

    public String getToken() {
        return token;
    }

    public Date getLast_used() {
        return last_used;
    }

    public String getUsername() {
        return username;
    }

    public String getSeries() {
        return series;
    }
}
