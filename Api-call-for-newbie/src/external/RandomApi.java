package external;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class RandomApi {
	private static final String URL = "http://jobs.github.com/positions.json?";
	
	public static void main(String[] args) {

		System.out.println("Hello newbie");
		System.out.println("Lets have our first api call. This api is called GitHub Jobs");
		RandomApi randomCall = new RandomApi(); 
		List<String> result = randomCall.search("java", "San+Diego", true);
		
		
	}
	
	public List<String> search (String description, String location, boolean fullTime) {
		String fullT = "false";
		if (fullTime) {
			fullT = "true";
		}
		String query = String.format("description=%s&location=%s&full_time=%s", description, location, fullT);
		
		try {
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return null;
	}
}
