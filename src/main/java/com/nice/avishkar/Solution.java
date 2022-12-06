package com.nice.avishkar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Solution {

	public static ArrayList<String[]> parseRows(Path csvFile){

		ArrayList<String[]> rowList = new ArrayList<String[]>();

		try (BufferedReader br = Files.newBufferedReader(csvFile)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineItems = line.split(",");
				rowList.add(lineItems);
			}
//			br.close();
		}
		catch(Exception e){

		}
		if(rowList.size() > 0) rowList.remove(0);
		return rowList;

	}

	public  static int winnerIndex(TreeSet<CandidateVotes> arr){
//		same first two
		if(arr.size() == 2){
			return 0;
		}
		Iterator<CandidateVotes> iterator = arr.iterator();

		long v1 =0,v2=0;
		v1 = iterator.next().getVotes();
		v2 = iterator.next().getVotes();


		if(v1 == v2){
			return -1;
		}
		return 0;
	}

	public static ElectionResult execute(Path candidateFile, Path votingFile) {



		Map<String,ConstituencyResult> result = new TreeMap<>();

		ArrayList<String[]> candidateD = parseRows(candidateFile);
		HashMap<Candidate, Long> candidateVotes = new HashMap<>();
		HashSet<String> constituencies = new HashSet<>();
		for(String[] item : candidateD){
			constituencies.add(item[0]);
			Candidate candidate = new Candidate(item[1], item[0]);
			candidateVotes.put(candidate, 0l);
		}

		for(String item : constituencies){
			candidateVotes.put(new Candidate("NOTA", item), 0l);
		}


		// Read the voting file and populate the votesMap

		ArrayList<String[]> votingData = parseRows(votingFile);
		HashMap<String,Long> voterUniqueCount = new HashMap<>();

		for(String[] item : votingData){
			String voterId = item[0];
			voterUniqueCount.put(voterId, voterUniqueCount.getOrDefault(voterId,0l)+1);
		}


		for(String[] item : votingData){

			String constituency = item[1];
			String candidate = item[3];
			String voterId = item[0];
			if(voterUniqueCount.get(voterId) > 1) continue;

			Candidate candidateObj = new Candidate(candidate, constituency);
			if(candidateVotes.containsKey(candidateObj)){
				candidateVotes.put(candidateObj, candidateVotes.get(candidateObj)+1);
			}else{
				candidateVotes.put(candidateObj, 1l);
			}



		}


		TreeMap<String, TreeSet<CandidateVotes>> resultMap = new TreeMap<>();

		for(Map.Entry<Candidate, Long> entry : candidateVotes.entrySet()){
			Candidate candidate = entry.getKey();
			long votes = entry.getValue();
			String constituency = candidate.getConstituencyName();
			String candidateName = candidate.getCandidatetName();

			if(resultMap.containsKey(constituency)){
				resultMap.get(constituency).add(new CandidateVotes(candidateName, votes));
			}else{
				TreeSet<CandidateVotes> curr = new TreeSet<>();
				curr.add(new CandidateVotes(candidateName, votes));
				resultMap.put(constituency, curr);
			}
		}

		for(Map.Entry<String, TreeSet<CandidateVotes>> x : resultMap.entrySet() ){
			int winnerIndex = winnerIndex(x.getValue());

			if(winnerIndex == -1){


				ConstituencyResult c1 = new ConstituencyResult("NO_WINNER",new ArrayList<>(x.getValue()));
				result.put(x.getKey(),c1);


			}else{
				ConstituencyResult c1 = new ConstituencyResult(x.getValue().first().getCandidateName(),new ArrayList<>(x.getValue()));
				result.put(x.getKey(),c1);
			}



		}


		ElectionResult resultData = new ElectionResult(result);
		
		return resultData;
	}



}