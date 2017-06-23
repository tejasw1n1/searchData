package Util;

import java.util.List;
import java.util.Random;

import DAO.FileDao;
import Model.FileDetails;

public class HierarchialClustering {

	public void clusterInputFile(FileDetails fileDetails){
		FileDao fd = new FileDao();
		List<FileDetails> allfileDetails = fd.getAllfiles();
		int size=allfileDetails.size();
		if(size>1){
			Random rand = new Random();
			int cluster = rand.nextInt(size) + 1;
			fileDetails.setCluster("c"+cluster);
		}else{
			fileDetails.setCluster("c"+1);
		}
	}
}
