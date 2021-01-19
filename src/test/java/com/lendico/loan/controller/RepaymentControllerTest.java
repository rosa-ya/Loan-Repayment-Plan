package com.lendico.loan.controller;

import com.lendico.loan.model.RepaymentRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RepaymentControllerTest{


    @Test
    public void testGetEmployeeListSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + 8080 + "/generatePlan";
        URI uri = new URI(baseUrl);
        RepaymentRequest request = new RepaymentRequest(24, 5.0, 5000, "2018-01-01T00:00:00Z");
        ResponseEntity<String> result = restTemplate.postForEntity(uri,request, String.class);
        //Verify request succeed
        Assert.assertEquals(202, result.getStatusCodeValue());
    }
}
