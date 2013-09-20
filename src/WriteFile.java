package com;

import java.util.*;
import java.io.*;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;

public class WriteFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   FileWriter writer = null;
		// TODO Auto-generated method stub
		try{
			
			FacebookClient facebookClient = new DefaultFacebookClient("****");
			System.setProperty("https.proxyHost", "***");
	        System.setProperty("https.proxyPort", "**"); 
	        System.setProperty("proxySet", "true"); 
	        
	        Connection<Post> home;
	        int i;

	        // limits to test
	        String[] limits = {"10", "100", "1000", "10000"};
	        for (String limit : limits) {
	            // get news feed
	            home = facebookClient.fetchConnection("me/home", Post.class, Parameter.with("limit", limit));
	            writer = new FileWriter("d:/test/big_output.txt", true);
	            // going through pages
	            i = 1;
	            for (List<Post> page : home) {
	                for (Post post : page) {
	                    // store into list
	                	 writer.write(post + "\n");	 	                    
	                }
	                i++;
	            }
	        }
	    writer.close();	
		}
		catch(IOException ie){
			ie.printStackTrace();
		}

	}

}
