package com.sacumen.salesforce.app.salesforce_dev.salesforcecontroller;


import com.force.api.ForceApi;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpsEnabled = true, httpsPort = 8082)//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesforceControllerTest {
    @Mock
    private ForceApi forceApiMock;


    @Test
    void fetchEventTesting(WireMockRuntimeInfo wmRuntimeInfo) {
        String responseBody = " {attributes={type=EventLogFile, url=/services/data/v39.0/sobjects/EventLogFile/0AT5i00002bvnnaGAA\n" +
                "        }, Id=0AT5i00002bvnnaGAA, EventType=ApiTotalUsage, LogFile=/services/data/v39.0/sobjects/EventLogFile/0AT5i00002bvnnaGAA/LogFile, LogDate=2023-06-02T00: 00: 00.000+0000, LogFileLength=683.0\n" +
                "    }";
        String apiUrl = "/fetchEvent";
        stubFor(get(apiUrl).willReturn(ok(responseBody)));
        String apiResponse = getContent(wmRuntimeInfo.getHttpBaseUrl() + apiUrl);
        assertEquals(apiResponse, responseBody);
        verify(getRequestedFor(urlEqualTo(apiUrl)));
    }
    private String getContent(String url) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        return testRestTemplate.getForObject(url, String.class);
    }


    @Test
    void fetchUserTesting(WireMockRuntimeInfo wmRuntimeInfo) {
        String responseBody = " {attributes={type=User, url=/services/data/v39.0/sobjects/User/0055i000009FdqSAAS\n" +
                "        }, Id=0055i000009FdqSAAS, Name=Tharun Kumar Janjeti, ProfileId=00e5i000004aVBTAA2, IsActive=true\n" +
                "    }";
        String apiUrl = "/fetchUsers";
        stubFor(get(apiUrl).willReturn(ok(responseBody)));
        String apiResponse = getUserContent(wmRuntimeInfo.getHttpBaseUrl() + apiUrl);
        assertEquals(apiResponse, responseBody);
        verify(getRequestedFor(urlEqualTo(apiUrl)));
    }
    private String getUserContent(String url) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        return testRestTemplate.getForObject(url, String.class);
    }


    @Test
    void fetchAccountTesting(WireMockRuntimeInfo wmRuntimeInfo) {
        String responseBody = " {attributes={type=Account, url=/services/data/v39.0/sobjects/Account/0015i00000lmhuvAAA\n" +
                "        }, Id=0015i00000lmhuvAAA, Name=GenePoint\n" +
                "}";
        String apiUrl = "/fetch-accounts";
        stubFor(get(apiUrl).willReturn(ok(responseBody)));
        String apiResponse = getAccountContent(wmRuntimeInfo.getHttpBaseUrl() + apiUrl);
        assertEquals(apiResponse, responseBody);
        verify(getRequestedFor(urlEqualTo(apiUrl)));
    }
    private String getAccountContent(String url) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        return testRestTemplate.getForObject(url, String.class);
    }


}


