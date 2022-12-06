package com.nice.avishkar;

import java.util.HashMap;
import java.util.Objects;

public class Candidate {
    private String candidateName;
    private String constituencyName;

    public Candidate() {
        super();
    }

    public Candidate(String candidateName, String constituencyName) {
        super();
        this.candidateName = candidateName;
        this.constituencyName = constituencyName;
    }

    public String getCandidatetName() {
        return candidateName;
    }

    public void setCandidateName(String name) {
        this.candidateName = name;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateName, constituencyName);
    }

    @Override
    public boolean equals(Object o) {
        Candidate candidate = (Candidate) o;
        boolean checkName = this.candidateName.equals(candidate.getCandidatetName());
        boolean checkConstituency = this.constituencyName.equals(candidate.getConstituencyName());
        if(checkConstituency && checkName){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Candidate [name=");
        builder.append(candidateName);
        builder.append(", constituency=");
        builder.append(constituencyName);
        builder.append("]");
        return builder.toString();
    }


}