package connection;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.*;
import com.util.FBUtil;

public class FetchPostfromPage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileWriter writer 	= null; 
		String searchString = null;
		
		try{
			System.out.print("Enter the search string: ");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	searchString = br.readLine();
			
	    	System.setProperty("https.proxyHost", "172.18.65.22");
		    System.setProperty("https.proxyPort", "80"); 
		    System.setProperty("proxySet", "true");
			
		    FacebookClient.AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken(FBUtil.myAppId, FBUtil.myAppSecretKey);
			
			FacebookClient client = new DefaultFacebookClient(accessToken.getAccessToken());
			
			Connection<Page> ps = client.fetchConnection("search", Page.class, Parameter.with("q", searchString), Parameter.with("type", "Page"),Parameter.with("limit", 500));
			int size=ps.getData().size();
			System.out.println(size);
			writer = new FileWriter("D:/test/24Sep/"+searchString+"_SearchData.txt", true);
			for(int i=0;i<size;i++){
				System.out.println(ps.getData().get(i).getId()+ "::"+ps.getData().get(i).getName());
				
				Connection<Post> postCon = client.fetchConnection(ps.getData().get(i).getId()+ "/feed", Post.class, Parameter.with("limit", 50));			
		        
		        writer.write(postCon.getData().toString()+ "\n");	        
				
			}
			writer.close();
		}catch(IOException io){
			
		}
	}

}
