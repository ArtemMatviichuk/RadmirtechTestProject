package com.test.radmirtech.dao.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EquipmentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime lastContactDate;
    private int currentVolume;
    private boolean hasError;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(LocalDateTime lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(int currentVolume) {
        this.currentVolume = currentVolume;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}

