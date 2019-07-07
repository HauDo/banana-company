package com.banana.request.service;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import com.banana.request.model.DataRequest;
import com.banana.request.model.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataRequestService {

     public static final String API_CHECKIN_URL = "http://localhost:3000/api/employee/checkin";
     public static final String API_CHECKOUT_URL = "http://localhost:3000/api/employee/checkout";

     public static DataRequestService createInstance() {
         return new DataRequestService();
     }

    public DataResponse checkIn() throws Exception {
    	DataRequest dataRequest = buildDataRequest("Emp01", "Nguyen Van A", "Canh Nau cf");
        String jsonDataRequest = convertToJson(dataRequest);
        Response response = getResponse(API_CHECKIN_URL, jsonDataRequest);
        return response.readEntity(DataResponse.class);
    }

    public DataResponse checkOut() throws Exception {
        DataRequest dataRequest = buildDataRequest("Emp01", "Nguyen Van A", "Canh Nau cf");
        String jsonDataRequest = convertToJson(dataRequest);
        Response response = getResponse(API_CHECKOUT_URL, jsonDataRequest);
        return response.readEntity(DataResponse.class);
    }

    private Response getResponse(String api, String jsonDataRequest) {
    	Client client = createRestClient();
    	WebTarget target = client.target(api);
    	return target.request(MediaType.APPLICATION_JSON_TYPE)
    			.post(Entity.entity(jsonDataRequest, MediaType.APPLICATION_JSON));
    }

	private DataRequest buildDataRequest(String empId, String account, String location) {
		DataRequest dataRequest = new DataRequest();
        dataRequest.setEmpId(empId);
        dataRequest.setAccount(account);
        dataRequest.setLocalTime(new Date());
        dataRequest.setLocation(location);
		return dataRequest;
	}

    private static Client createRestClient() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.register(
                new LoggingFeature(
                        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO,
                        LoggingFeature.Verbosity.PAYLOAD_ANY,
                        5000));

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
