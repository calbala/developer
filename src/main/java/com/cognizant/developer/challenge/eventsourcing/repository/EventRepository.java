package com.cognizant.developer.challenge.eventsourcing.repository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

@Component
public class EventRepository {

	public String listAllEvents() {
		RestClient client = null;
		String evres = "{ }";
		try {
			client = RestClient.builder(
			                new HttpHost("localhost", 9200, "http")).build();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("q", "*:*");
			paramMap.put("pretty", "true");
			Response queryresponse = client.performRequest("GET", "/events/_search", paramMap);
			evres = EntityUtils.toString(queryresponse.getEntity());
		} catch (Exception ex) {
	        ex.printStackTrace();		
		} finally {
			try {
				if(client != null) client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return evres;
	}

	public boolean save(String message) {
		boolean persisted = false;
		RestClient client = null;
		try {
			client = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
			Map<String, String> params = Collections.emptyMap();
			HttpEntity entity = new NStringEntity(message, ContentType.APPLICATION_JSON);
			String indexLocation = "/events/doc/"+ UUID.randomUUID();
			Response response = client.performRequest("PUT", indexLocation, params, entity);
			System.out.println(EntityUtils.toString(response.getEntity()));
			persisted = true;
		} catch (Exception ex) {
			
		} finally {
			try {
				if(client != null) client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return persisted;
	}

}
