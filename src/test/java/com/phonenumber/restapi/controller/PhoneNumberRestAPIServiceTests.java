package com.phonenumber.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* This class has been written to test the REST API class- PhoneNumberAPIService.
 */

@WebMvcTest(PhoneNumberAPIService.class)
public class PhoneNumberRestAPIServiceTests {

    private static final String END_POINT_PATH = "http://localhost:8080/api/v1/phoneNumbers";

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockitoBean
    private PhoneNumberAPIService service;

    @Test
    public void testGetAllPhoneNumbersShouldReturn200_Ok() throws Exception {

        Mockito.when(service.getAllPhoneNumbers()).thenReturn(ResponseEntity.ok(new ArrayList<String>(
                Arrays.asList("0411111111","0412222222","0413333333"))));

        mockMvc.perform(MockMvcRequestBuilders.
                get(END_POINT_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "[\"0411111111\",\"0412222222\",\"0413333333\"]"))
                .andDo(print());

    }

    @Test
    public void testGetPhoneNumbersForCustomerShouldReturn200_Ok() throws Exception {
        long customerId = 1234L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(service.getPhoneNumbersForCustomer(customerId)).thenReturn(ResponseEntity.ok(new ArrayList<String>(
                Arrays.asList("0411111111","0412222222"))));

        mockMvc.perform(MockMvcRequestBuilders.
                        get(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "[\"0411111111\",\"0412222222\"]"))
                .andDo(print());

    }

    @Test
    public void testActivatePhoneNumberShouldReturn200_Ok() throws Exception {
        long customerId = 1234L;
        String phoneNumber = "0413234567";

        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(service.activatePhoneNumber(customerId,phoneNumber)).thenReturn(ResponseEntity.ok("Phone number activated successfully"));

        mockMvc.perform(put(requestURI).contentType("application/json").content(phoneNumber))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetAllPhoneNumbersShouldReturn404_NotFound() throws Exception {
        Mockito.when(service.getAllPhoneNumbers()).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.
                        get(END_POINT_PATH))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andDo(print());

    }

    @Test
    public void testGetAllPhoneNumbersForCustomerShouldReturn404_NotFound() throws Exception {
        long customerId = 1234L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(service.getPhoneNumbersForCustomer(customerId)).thenReturn(
                ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.
                        get(requestURI))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andDo(print());
    }

    @Test
    public void testActivatePhoneNumberShouldReturn400_BadRequest() throws Exception {
        long customerId = 1234L;

        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(service.activatePhoneNumber(customerId,null)).thenReturn(
                ResponseEntity.badRequest().build());

        mockMvc.perform(put(requestURI).contentType("application/json").content(""))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testActivatePhoneNumberShouldReturn409_NoActivationError() throws Exception {
        long customerId = 1234L;
        String phoneNumber = "0413234567";

        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(service.activatePhoneNumber(customerId,phoneNumber)).thenReturn(
                ResponseEntity.notFound().build());

        mockMvc.perform(put(requestURI).contentType("application/json").content(phoneNumber))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andDo(print());
    }
}
