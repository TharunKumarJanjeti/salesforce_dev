package com.sacumen.salesforce.app.salesforce_dev.salesforceservice;

import com.force.api.ForceApi;
import com.force.api.QueryResult;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    public class SalesforceServiceTest {

    @Mock
    private ForceApi forceApiMock;

    @InjectMocks
    @Spy
    private SalesforceService salesforceService;

    @Test
    public void testRetrieveEventData() {
        doReturn(forceApiMock).when(salesforceService).createSaleforeceConnection();

        // Mock the query result
        QueryResult mockQueryResult = new QueryResult<>();
        List<Map<String, Object>> records = new ArrayList<>();
        Map record = new HashMap<>();
        record.put("Id", "123");
        record.put("EventType", "Event");
        record.put("LogFile", "log.txt");
        record.put("LogDate", "2023-05-30");
        record.put("LogFileLength", 1024);
        records.add(record);
        mockQueryResult.setRecords(records);
        when(forceApiMock.query("SELECT Id,EventType,LogFile,LogDate,LogFileLength FROM EventLogFile"))
                .thenReturn(mockQueryResult);

        // Invoke the method to be teste
        ForceApi forceApiMock = mock(ForceApi.class);
        salesforceService.setForceApi(forceApiMock);
        String result = salesforceService.retrieveEventData();
        // Verify the result
        assertEquals("[{LogFile=log.txt, EventType=Event, LogFileLength=1024, Id=123, LogDate=2023-05-30}]", result);
    }




}


