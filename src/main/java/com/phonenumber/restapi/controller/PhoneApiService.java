package com.phonenumber.restapi.controller;

import com.phonenumber.restapi.model.CustomerPhone;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class PhoneApiService {

    public static Map<Long, List<CustomerPhone>> customerPhoneMap = createMap();

    /**
     * Get all phone numbers
     *
     * @return the ResponseEntity with status 200 (OK) and with body as the phone numbers list, or with status 404 (Not Found) if the product does not exist
     */
    @GetMapping("/phoneNumbers")
    public ResponseEntity<List<String>> getAllPhoneNumbers() {
        return ResponseEntity.ok(getPhoneNumbers());
    }

    /**
     * Get all phone numbers for the given customer
     *
     * @param customerId the ID of the customer
     * @return the ResponseEntity with status 200 (OK) and with body as the phone numbers list, or with status 404 (Not Found) if the product does not exist
     */
    @GetMapping("/phoneNumbers/{customerId}")
    public ResponseEntity<List<String>> getPhoneNumbersForCustomer(@PathVariable long customerId) {
        return ResponseEntity.ok(getPhoneNumbers(customerId));
    }

    /**
     * Activate a phone number
     *
     * @param phoneNumber the phone number to be activated
     * @return the ResponseEntity with status 200 (OK) and with body as the message "Phone number activated successfully" , or with status 404 (Not Found) if the product does not exist
     */
    @PutMapping("/phoneNumbers/{customerId}")
    public ResponseEntity<String> activatePhoneNumber(@PathVariable Long customerId, @RequestBody String phoneNumber) {
       if(phoneNumber == null || phoneNumber.isEmpty()) {
           return ResponseEntity.badRequest().body("Error: Phone number is not provided!!");
       }
        if (!activatePhoneNumberForCustomer(customerId, phoneNumber)) {
            return ResponseEntity.ok("Phone number could not be activated");
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
