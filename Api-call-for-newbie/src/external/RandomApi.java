package external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jobs.Job;
import jobs.Job.JobBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

public class RandomApi {
	private static final String GITURL = "https://jobs.github.com/positions.json";
	
	public static void main(String[] args) {
		
		System.out.println("Hello newbie");
		System.out.println("Lets have our first api call. This api is called GitHub Jobs");
		RandomApi randomCall = new RandomApi(); 
		List<Job> result = randomCall.search("java", "", true);
		
		// System.out.println(result.size());
		
		if (result.size() >= 50) {
			System.out.println("\nYou have a good chance to get your job \nthere are more than 50 jobs out there");
		} else if (result.size() >= 30){
			System.out.println("\nyou might get hired");
		} else {
			System.out.println("\nYou are becoming a homeless");
		}
		System.out.println("\n\n<--- The end of the newbiew projoec --->");
	}	
	
	public List<Job> search (String description, String location, boolean fullTime) {
		List<Job> result = new ArrayList<>();
		
		// make sure the input have valid encoding
		try {
			description = java.net.URLEncoder.encode(description, "UTF-8");
			location = java.net.URLEncoder.encode(location, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = String.format("description=%s&full_time=%s", description, fullTime);
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(GITURL + "?" + query).openConnection();
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				System.out.println("\ngood job newbie >>> Successfully made a connection to the api >>>");
			} else {
				System.out.println("GG bro... something went wrong. Response code is not 200");
			}
			
			// Read one line at once using buffer reader (faster)
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close(); // close the stream
			connection.disconnect(); // disconnection from the api
			
			// now I want to store the data into Jobs data type that I created
			
			String arrString = response.toString();
			JSONArray objArray = new JSONArray(arrString);
			int length = objArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject obj = objArray.getJSONObject(i);
				JobBuilder builder = new JobBuilder();
				
				if (!obj.isNull("id")) {
					builder.setId(obj.getString("id"));
				}
				if (!obj.isNull("url")) {
					builder.setUrl(obj.getString("url"));
				}
				if (!obj.isNull("company")) {
					builder.setCompany(obj.getString("company"));
				}
				if (!obj.isNull("title")) {
					builder.setTitle(obj.getString("title"));
				}
				
				result.add(builder.build());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
