package com.banana.datarequest;

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

import com.banana.timer.DataRequest;
import com.banana.timer.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataRequestService {

     public static final String API_URL = "http://localhost:3000/api/employee/checkin";

     public static DataRequestService createInstance() {
         return new DataRequestService();
     }

    public DataResponse checkIn() {
        DataRequest dataRequest = new DataRequest();
       /* dataRequest.setEmpId(dataRequest.getEmpId());
        dataRequest.setAccount(dataRequest.getAccount());
        dataRequest.setLocalTime(dataRequest.getLocalTime());
        dataRequest.setLocation(dataRequest.getLocation());*/

        dataRequest.setEmpId("test");
        dataRequest.setAccount("ky");
        dataRequest.setLocalTime(new Date());
        dataRequest.setLocation("Canh Nau cf");

        String jsonDataRequest = convertToJson(dataRequest);

        Client client = createJerseyRestClient();
        WebTarget target = client.target(API_URL);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(jsonDataRequest, MediaType.APPLICATION_JSON));

        return response.readEntity(DataResponse.class);
    }

    private static Client createJerseyRestClient() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.register( //
                new LoggingFeature( //
                        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), //
                        Level.INFO, //
                        LoggingFeature.Verbosity.PAYLOAD_ANY, //
                        10000));

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
