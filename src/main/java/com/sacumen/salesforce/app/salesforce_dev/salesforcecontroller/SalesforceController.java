package com.sacumen.salesforce.app.salesforce_dev.salesforcecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sacumen.salesforce.app.salesforce_dev.salesforceservice.SalesforceService;

@RestController
public class SalesforceController {
    @Autowired
    private SalesforceService salesforceService;



    @RequestMapping("/fetch-accounts")//change name
    public String getSalesforceAccountData() {
            System.out.println("Reached");
        return salesforceService.retrieveaccountData();
    }

    /**
     *
     * returns users data
     */
    @RequestMapping("/fetchUsers")
    public String getUserData() {
        System.out.println("Reached");
        return salesforceService.retrievUserData();
    }
    @RequestMapping("/fetchEvent")
    public String getEventData() {
        System.out.println("Reached");
        return salesforceService.retrieveEventData();
    }

    }































































//    @RequestMapping("/callback")
//    public String callback() {
//        System.out.println("callback");
//        return "callback is called";
//    }