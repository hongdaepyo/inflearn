package com.dphong.inflearnthejavatest.domain;

import com.dphong.inflearnthejavatest.StudyStatus;

import java.time.LocalDateTime;

public class Study {

    private StudyStatus status;

    private int limitCount;
    private String name;
    private LocalDateTime openedDateTime;
    private Long ownerId;

    public Study(int limitCount, String name) {
        this.limitCount = limitCount;
        this.name = name;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }

        this.limitCount = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limitCount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limitCount +
                ", name='" + name + '\'' +
                '}';
    }

    public void setOwnerId(Long memberId) {
    }

    public void open() {
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }
}
