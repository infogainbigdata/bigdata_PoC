package com;

import java.io.*;
import java.util.regex.*;


public class FindKeywordInFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String searchString = null,
			dataFileLocation= null,
				line		= null;
		FileWriter writer = null; 
		boolean flag = false; 
	   	int linecount = 0;
          
	      try {
	  		  System.out.print("Enter the search string: ");		
			
	  		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	  searchString = br.readLine();
	    	  
	    	  System.out.print("Enter the file location: ");	
	    	  dataFileLocation = br.readLine();    	  
    	  
	    	  BufferedReader bf = new BufferedReader(new FileReader(dataFileLocation+".txt"));
	    	  Pattern p = Pattern.compile("\\b"+searchString+"\\b", Pattern.CASE_INSENSITIVE);	    	  
	    	  
	    	  writer = new FileWriter(dataFileLocation+searchString+".txt", true);
	    	  while ( (line = bf.readLine()) != null) {
	    		  flag=false;
	    	      linecount++;

	    	      Matcher m = p.matcher(line);
	    	    
	    	      while (m.find()) {
	    	          System.out.println("Word was found at position " + 
	    	                         m.start() + " on line " + linecount);
	    	          flag=true;
	    	      }
	    	      if(flag == true)
	    	    	  writer.write(line+"\n");	
	    	      }
	    	  writer.close();
	    	  
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to read search string!");
	         System.exit(1);
	      }

	}

}
