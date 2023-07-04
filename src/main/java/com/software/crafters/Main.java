package com.software.crafters;

import com.software.crafters.DefaultApi;
import com.software.crafters.model.GetCityRequestContent;
import com.software.crafters.model.GetCityResponseContent;
import com.software.crafters.invoker.ApiException;

public class Main {

    public static void main(String[] args) throws ApiException {
        DefaultApi api = new DefaultApi();
        api.setCustomBaseUrl("https://smithyexample.free.beeceptor.com");

        GetCityRequestContent getCityRequestContent = new GetCityRequestContent();
        getCityRequestContent.setCityId("exampleId");

        GetCityResponseContent response = api.getCity(getCityRequestContent);


    }
}
