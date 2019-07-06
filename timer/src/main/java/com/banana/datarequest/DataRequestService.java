package com.banana.datarequest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.banana.timer.DataRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataRequestService {

	 public static final String API_URL = "http://test";

	 public static DataRequestService createInstance() {
		 return new DataRequestService();
	 }

    public Integer checkIn() {
        DataRequest dataRequest = new DataRequest();
        dataRequest.setUserId(dataRequest.getUserId());
        dataRequest.setAccount(dataRequest.getAccount());
        dataRequest.setLocalTime(dataRequest.getLocalTime());
        dataRequest.setLocation(dataRequest.getLocation());

        String jsonDataRequest = convertToJson(dataRequest);

        Client client = createJerseyRestClient();
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(jsonDataRequest, MediaType.APPLICATION_JSON));
        return response.readEntity(Integer.class);
    }

    private static Client createJerseyRestClient() {
    	ClientConfig clientConfig = new ClientConfig();
        return ClientBuilder.newClient(clientConfig);
    }

    private static String convertToJson(DataRequest dataRequest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dataRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
