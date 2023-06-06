package com.sacumen.salesforce.app.salesforce_dev.salesforcecontroller;


import com.force.api.ForceApi;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.sacumen.salesforce.app.salesforce_dev.salesforceservice.SalesforceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpsEnabled = true, httpsPort = 8080)//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesforceControllerTest {
    @Mock
    private ForceApi forceApiMock;
    @InjectMocks
    @Spy
    private SalesforceService salesforceService;


    @Test
    void fetchEventTesting(WireMockRuntimeInfo wmRuntimeInfo) {
        String responseBody = " {attributes={type=EventLogFile, url=/services/data/v39.0/sobjects/EventLogFile/0AT5i00002bvnnaGAA\n" +
                "        }, Id=0AT5i00002bvnnaGAA, EventType=ApiTotalUsage, LogFile=/services/data/v39.0/sobjects/EventLogFile/0AT5i00002bvnnaGAA/LogFile, LogDate=2023-06-02T00: 00: 00.000+0000, LogFileLength=683.0\n" +
                "    }";
        String apiUrl = "/fetchEvent";

        //Define stub
        stubFor(get(apiUrl).willReturn(ok(responseBody)));

        //Hit API and check response
        String apiResponse = getContent(wmRuntimeInfo.getHttpBaseUrl() + apiUrl);
        assertEquals(apiResponse, responseBody);

        //Verify API is hit
        verify(getRequestedFor(urlEqualTo(apiUrl)));
    }

    private String getContent(String url) {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        return testRestTemplate.getForObject(url, String.class);
    }
}


