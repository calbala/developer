package com.cognizant.developer.challenge.elasticsearch;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class SearchTest {

	public static void main(String[] args) {
		System.out.println("Standalone Test on Elastic Search.");
		RestClient client = null;
		try {
			client = RestClient.builder(
			                new HttpHost("localhost", 9200, "http")).build();
			Map<String, String> params = Collections.emptyMap();
			String jsonString = "{" +
			            "\"user\":\"kimchy\"," +
			            "\"postDate\":\"2013-01-30\"," +
			            "\"message\":\"trying out Elasticsearch\"" +
			        "}";
			HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
			Response response = client.performRequest("PUT", "/posts/doc/1", params, entity);
			System.out.println(EntityUtils.toString(response.getEntity()));
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("q", "user:kimchy");
			paramMap.put("pretty", "true");
			                               
			Response queryresponse = client.performRequest("GET", "/posts/_search",
			                                                           paramMap);
			System.out.println(EntityUtils.toString(queryresponse.getEntity()));
			System.out.println("Host -" + queryresponse.getHost() );
			System.out.println("RequestLine -"+ queryresponse.getRequestLine() );
			
		} catch (Exception ex) {
			
		} finally {
			try {
				if(client != null) client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		 
		
		
		
	}

}
