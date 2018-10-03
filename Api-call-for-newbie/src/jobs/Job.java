package jobs;

import java.util.*;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Job {
	private String id;
	private String url;
	private String company;
	private String title;
	
	private Job(JobBuilder builder) {
		this.id = builder.id;
		this.url = builder.url;
		this.company = builder.company;
		this.title = builder.title;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getTitle() {
		return title;
	}
	
	public static class JobBuilder {
		private String id;
		private String url;
		private String company;
		private String title;
		
		public JobBuilder setId(String id) {
			this.id = id;
			return this;
		}
		
		public JobBuilder setUrl(String url) {
			this.url = url;
			return this;
		}		public JobBuilder setCompany(String company) {
			this.company = company;
			return this;
		}		public JobBuilder setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public Job build() {
			return new Job(this);
		}
	}
}
