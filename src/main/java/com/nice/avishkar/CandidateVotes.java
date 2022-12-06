package com.nice.avishkar;

import java.util.Comparator;
import java.util.Objects;

public class CandidateVotes implements Comparable<CandidateVotes> {

	private String  candidateName;
	private long votes;
	
	public CandidateVotes() {
		super();
	}

	public CandidateVotes(String candidateName, long votes) {
		super();
		this.candidateName = candidateName;
		this.votes = votes;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public long getVotes() {
		return votes;
	}

	public void setVotes(long votes) {
		this.votes = votes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidateName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateVotes other = (CandidateVotes) obj;
		return Objects.equals(candidateName, other.candidateName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CandidateVotes [candidateName=");
		builder.append(candidateName);
		builder.append(", votes=");
		builder.append(votes);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int compareTo(CandidateVotes c){
		if(this.candidateName.equals("NOTA")){
			return 1;
		}
		else if(c.candidateName.equals("NOTA")){
			return  -1;
		}
		else if(c.votes == this.votes){
			return this.candidateName.compareTo(c.candidateName);
		}
		else{
			if(c.votes > this.votes) return 1;
			return -1;
		}
	}

	
	
}
