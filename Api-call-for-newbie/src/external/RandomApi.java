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
import org.json.JSONException;
import org.json.JSONObject;

public class RandomApi {
	private static final String GITURL = "https://jobs.github.com/positions.json";
	
	public static void main(String[] args) {

		System.out.println("Hello newbie");
		System.out.println("Lets have our first api call. This api is called GitHub Jobs");
		RandomApi randomCall = new RandomApi(); 
		randomCall.search("java", "", true);
		
		
	}
	
	public void search (String description, String location, boolean fullTime) {
		List<String> result = new ArrayList<>();
		try {
			description = java.net.URLEncoder.encode(description, "UTF-8");
			location = java.net.URLEncoder.encode(location, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query = String.format("description=%s&full_time=%s", description, fullTime);
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(GITURL).openConnection();
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				System.out.println("good job newbie, you just successfully made the connection to the api");
			} else {
				System.out.println("GG bro... sth went wrong");
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close(); // close the stream
			connection.disconnect(); // disconnection from the api
			
			JSONObject obj = new JSONObject(response.toString());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
