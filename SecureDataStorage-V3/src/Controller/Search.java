package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FileDao;
import Model.FileDetails;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String searchText = request.getParameter("searchText");
		String sort = request.getParameter("sort");
		String cluster = request.getParameter("cluster");
		
    	long startTime = System.nanoTime();
		
		cluster = cluster.trim();
		searchText = searchText.trim();
		String []searchTextArr = null;
		if(searchText != null && !searchText.isEmpty()){
			searchTextArr = searchText.split(",");
		}
		sort = sort.trim();
		
		FileDao fileDao = new FileDao();
		List<FileDetails> files = fileDao.getAllfiles();
		Map<String, List<Integer>> keywordIndexMap = new HashMap<String, List<Integer>>();
		for(int i=0; i < files.size(); i++){
			FileDetails file = files.get(i);
			String keywords = file.getKeywords();
			if(keywords != null && !keywords.isEmpty()){
				String[] keywordsArr = keywords.split(",");
				if(keywordsArr.length > 0){
					for(int j=0; j < keywordsArr.length; j++){
						if(keywordIndexMap.containsKey(keywordsArr[j])){
							keywordIndexMap.get(keywordsArr[j]).add(Integer.valueOf(i));
						}else{
							List<Integer> tempList = new ArrayList<Integer>();
							tempList.add(Integer.valueOf(i));
							keywordIndexMap.put(keywordsArr[j].trim(), tempList);
						}
					}
				}
			}
		}
		Set<FileDetails> searchResult=new HashSet<FileDetails>();
		if(searchTextArr != null && searchTextArr.length>0){
			for(int i=0; i < searchTextArr.length; i++){
				if(keywordIndexMap.containsKey(searchTextArr[i].trim())){
					List<Integer> indexes = keywordIndexMap.get(searchTextArr[i].trim());
					for(Integer index: indexes){
						searchResult.add(files.get(index));
					}
				}
			}
		}else{
			if(cluster != null && !cluster.isEmpty()){
				for(int i=0; i < files.size(); i++){
					if(files.get(i).getCluster().equals(cluster)){
						searchResult.add(files.get(i));
					}
				}
			}else{
				searchResult.addAll(files);
			}
		}
		
		List<FileDetails> searchResultList = new ArrayList<FileDetails>(searchResult);
		if(sort != null && !sort.isEmpty() && sort.equals("rank")){
			Collections.sort(searchResultList, new Comparator<FileDetails>() {
				@Override
				public int compare(FileDetails f1, FileDetails f2){
					Integer rank1 = f1.getRank();
					Integer rank2 = f2.getRank();
					return rank2.compareTo(rank1);
				}
			});
		}
		
		if(sort != null && !sort.isEmpty() && sort.equals("dateasc")){
			Collections.sort(searchResultList, new Comparator<FileDetails>() {
				@Override
				public int compare(FileDetails d1, FileDetails d2){
					return d1.getDateInMillis().compareTo(d2.getDateInMillis());
				}
			});
		}
		if(sort != null && !sort.isEmpty() && sort.equals("datedesc")){
			Collections.sort(searchResultList, new Comparator<FileDetails>() {
				@Override
				public int compare(FileDetails d1, FileDetails d2){
					return d2.getDateInMillis().compareTo(d1.getDateInMillis());
				}
			});
		}

		request.setAttribute("files", searchResultList);
		 
		long endTime = System.nanoTime();
		System.out.println("Time Taken is "+(endTime - startTime)/1000000+" ms");
		 RequestDispatcher view = request.getRequestDispatcher("receiverwelcome.jsp");
	        view.forward(request, response);
		PrintWriter out = response.getWriter();

		out.print(
				"<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>TPA</title><script type='text/javascript'>"
						+ "function display(msg){alert(msg);}</script></head>"
						+ "<body><script type='text/javascript'>display('User Registered Successfully... ');window.location='http://localhost:8080/SecureDataStorage-V3/userlogin.jsp';</script></body></html>");

	}

}
