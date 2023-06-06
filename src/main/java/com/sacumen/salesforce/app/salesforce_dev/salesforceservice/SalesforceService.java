package com.sacumen.salesforce.app.salesforce_dev.salesforceservice;

import com.force.api.ApiConfig;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service
public class SalesforceService {

    public void setForceApi(ForceApi forceApi) {
        this.forceApi = forceApi;
    }

    private  ForceApi forceApi;

    @Value("${spring.salesforce.username}") String username;
    @Value("${spring.salesforce.password}") String password;
    @Value("${spring.salesforce.client-id}") String clientId;
    @Value("${spring.salesforce.client-secret}") String clientSecret;

    @Value("${spring.salesforce.security-token}") String securityToken;


    public ForceApi createSaleforeceConnection(){

        ApiConfig config = new ApiConfig();
        config.setUsername(username);
        config.setPassword(password+securityToken);
        config.setClientId(clientId);
        config.setClientSecret(clientSecret);

        forceApi = new ForceApi(config);

        return forceApi;
    }


    // Example method to retrieve Salesforce data
    public String retrieveaccountData() {
        ForceApi   forceApi = createSaleforeceConnection();
        QueryResult<Map> resultMap=forceApi.query("SELECT Id, Name FROM Account");
        System.out.println(resultMap.getRecords());
        return resultMap.getRecords().toString();
    }

    public String retrievUserData() {
        ForceApi   forceApi = createSaleforeceConnection();
        QueryResult<Map> resultMap=forceApi.query("SELECT Id,name,ProfileId,IsActive FROM User");
        System.out.println(resultMap.getRecords());
        return resultMap.getRecords().toString();
    }
    public String retrieveEventData() {
        ForceApi forceApi = createSaleforeceConnection();
        QueryResult<Map> resultMap=forceApi.query("SELECT Id,EventType,LogFile,LogDate,LogFileLength FROM EventLogFile");
        System.out.println(resultMap.getRecords());
        return resultMap.getRecords().toString();
    }




}
