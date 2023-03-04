package com.guncetek.abc.app.algorithm.entities;

public class Algorithm {
    private int limit;
    private int candidateLowNumber,candidateHighNumber;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Algorithm(int limit, int candidateLowNumber, int candidateHighNumber) {
        if(limit <=0 || candidateLowNumber>=candidateHighNumber)
            throw new IllegalArgumentException();
        this.limit = limit;
        this.candidateLowNumber = candidateLowNumber;
        this.candidateHighNumber = candidateHighNumber;
    }

    public int getLimit() {
        return limit;
    }

    public int getCandidateLowNumber() {
        return candidateLowNumber;
    }

    public void setCandidateLowNumber(int candidateLowNumber) {
        this.candidateLowNumber = candidateLowNumber;
    }

    public int getCandidateHighNumber() {
        return candidateHighNumber;
    }

    public void setCandidateHighNumber(int candidateHighNumber) {
        this.candidateHighNumber = candidateHighNumber;
    }
}
