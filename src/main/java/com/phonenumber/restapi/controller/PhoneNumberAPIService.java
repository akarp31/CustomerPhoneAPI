package com.phonenumber.restapi.controller;

import com.phonenumber.restapi.exception.ErrorMessages;
import com.phonenumber.restapi.exception.PhoneNumberActivationException;
import com.phonenumber.restapi.exception.PhoneNumberNotFoundException;
import com.phonenumber.restapi.exception.PhoneNumberNotProvidedException;
import com.phonenumber.restapi.model.CustomerPhone;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/* This is the service class that provides the REST API for retrieving and activation phone numbers.
 */
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/phoneNumbers")
public class PhoneNumberAPIService {

    public static Map<Long, List<CustomerPhone>> customerPhoneMap = createMap();

    /* This method retrieves all the phone numbers in the system, for all the customers
     * @return the ResponseEntity with status 200 (OK) and with body as the phone numbers list,
     * or with status 404 (Not Found) if the phone numbers do not exist
     */
    @Valid
    @ResponseBody
    @GetMapping()
    @Operation(summary = "This API retrieves all the phone numbers in the system, for all the customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone numbers are retrieved successfully",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Phone numbers not found",
                    content = @Content) })
    public ResponseEntity<List<String>> getAllPhoneNumbers() {
        List<String> phoneList = getPhoneNumbers();
        if (phoneList.isEmpty()) {
            throw new PhoneNumberNotFoundException(ErrorMessages.ERROR_PHONE_NUMBER_NOT_FOUND);
        } else {
            return ResponseEntity.ok(phoneList);
        }
    }

    /**
     * This method retrieves all the phone numbers for a given customer
     * @param customerId the ID of the customer
     * @return the ResponseEntity with status 200 (OK) and with body as the phone numbers list,
     * or with status 404 (Not Found) if the phone numbers do not exist
     */
    @Valid
    @ResponseBody
    @GetMapping("/{customerId}")
    @Operation(summary = "This API retrieves all the phone numbers for the given customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone numbers are retrieved successfully",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Phone numbers not found",
                    content = @Content) })
    public ResponseEntity<List<String>> getPhoneNumbersForCustomer(@PathVariable long customerId) {
        List<String> phoneList = getPhoneNumbers(customerId);
        if (phoneList.isEmpty()) {
            throw new PhoneNumberNotFoundException(ErrorMessages.ERROR_PHONE_NUMBER_NOT_FOUND_FOR_CUSTOMER + customerId);
        } else {
            return ResponseEntity.ok(phoneList);
        }
    }

    /**
     * Activate a phone number
     *
     * @param phoneNumber the phone number to be activated
     * @return the ResponseEntity with
     * status 200 (OK) and with body as the message "Phone number activated successfully" ,
     * or with status 400 (Bad request) if the phone number has not been provided
     * or with status 409 (Conflict) if the phone numbers cannot be activated
     */
    @Valid
    @ResponseBody
    @PutMapping("/{customerId}")
    @Operation(summary = "This API retrieves activates the given phone number for the given customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone number successfully activated",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Phone number has not been provided",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "409", description = "Phone numbers cannot be activated",
                    content = @Content) })
    public ResponseEntity<String> activatePhoneNumber(@PathVariable Long customerId, @RequestBody String phoneNumber) {
       if(phoneNumber == null || phoneNumber.isEmpty()) {
           throw new PhoneNumberNotProvidedException(ErrorMessages.ERROR_PHONE_NUMBER_NOT_PROVIDED + customerId);
       }
        if (!activatePhoneNumberForCustomer(customerId, phoneNumber)) {
            System.out.println(ErrorMessages.ERROR_PHONE_NUMBER_CANNOT_BE_ACTIVATED);
            throw new PhoneNumberActivationException(ErrorMessages.ERROR_PHONE_NUMBER_CANNOT_BE_ACTIVATED);
        } else return ResponseEntity.ok("Phone number activated successfully");
    }

    private static Map<Long, List<CustomerPhone>> createMap() {
        CustomerPhone phone1 = new CustomerPhone(1L, 1L, "0411234234", false);
        CustomerPhone phone2 = new CustomerPhone(2L, 1L, "0411234567", false);
        CustomerPhone phone3 = new CustomerPhone(3L, 2L, "0411111111", false);
        CustomerPhone phone4 = new CustomerPhone(4L, 2L, "0411111122", false);
        CustomerPhone phone5 = new CustomerPhone(1L, 2L, "0411111133", false);
        CustomerPhone phone6 = new CustomerPhone(2L, 2L, "0411111144", false);
        CustomerPhone phone7 = new CustomerPhone(3L, 3L, "0411111155", false);
        CustomerPhone phone8 = new CustomerPhone(4L, 3L, "0411111166", false);
        CustomerPhone phone9 = new CustomerPhone(1L, 3L, "0411111177", false);
        CustomerPhone phone10 = new CustomerPhone(2L, 4L, "0411111188", false);
        CustomerPhone phone11= new CustomerPhone(3L, 4L, "0411111199", false);
        CustomerPhone phone12 = new CustomerPhone(4L, 4L, "041111120", false);
        CustomerPhone phone13 = new CustomerPhone(1L, 4L, "0411234221", false);
        CustomerPhone phone14 = new CustomerPhone(2L, 4L, "0411111125", false);
        CustomerPhone phone15 = new CustomerPhone(3L, 5L, "0411111155", false);
        CustomerPhone phone16 = new CustomerPhone(4L, 5L, "0411789778", false);
        CustomerPhone phone17 = new CustomerPhone(1L, 5L, "0411234279", false);
        CustomerPhone phone18 = new CustomerPhone(2L, 5L, "0411234577", false);
        CustomerPhone phone19 = new CustomerPhone(3L, 5L, "0411111166", false);
        CustomerPhone phone20 = new CustomerPhone(4L, 5L, "0411789777", false);

        customerPhoneMap = Map.ofEntries(
                Map.entry(1L, new ArrayList<CustomerPhone>(Arrays.asList(phone1, phone2))),
                Map.entry(2L, new ArrayList<CustomerPhone>(Arrays.asList(phone3, phone4,phone5,phone6))),
                Map.entry(3L, new ArrayList<CustomerPhone>(Arrays.asList(phone7, phone8,phone9))),
                Map.entry(4L, new ArrayList<CustomerPhone>(Arrays.asList(phone10, phone11,phone12,phone13,phone14))),
                Map.entry(5L, new ArrayList<CustomerPhone>(Arrays.asList(phone15, phone16,phone17,phone18,phone19,phone20)))
                );

        return customerPhoneMap;
    }

    private static List<String> getPhoneNumbers(Long customerId) {
        List<String> phonesList = new ArrayList<>();
        if(customerPhoneMap.containsKey(customerId)) {
            Iterator iterator = customerPhoneMap.get(customerId).iterator();
            while(iterator.hasNext()) {
                CustomerPhone phoneRecord = (CustomerPhone) iterator.next();
                phonesList.add(phoneRecord.getPhoneNumber());
            }
        }
        return phonesList;
    }

    private boolean activatePhoneNumberForCustomer(Long customerId, String phoneNumber) {
        boolean result = false;
        if(customerPhoneMap.containsKey(customerId)) {
            Iterator iterator = customerPhoneMap.get(customerId).iterator();

            while(iterator.hasNext()) {
                CustomerPhone phoneRecord = (CustomerPhone) iterator.next();

                if(phoneRecord.getPhoneNumber().equals(phoneNumber) ) {
                   phoneRecord.setActivated(true);
                   result = true;
                }
            }
        }
        return result;
    }

    private static List<String> getPhoneNumbers() {
        List<String> phonesList = new ArrayList<>();
        for (Map.Entry<Long, List<CustomerPhone>> entry : (Iterable<Map.Entry<Long, List<CustomerPhone>>>)
                customerPhoneMap.entrySet()) {
            Iterator iterator = entry.getValue().iterator();
            while(iterator.hasNext()) {
                CustomerPhone phoneRecord = (CustomerPhone) iterator.next();
                phonesList.add(phoneRecord.getPhoneNumber());
            }
        }
        return phonesList;
    }
}
