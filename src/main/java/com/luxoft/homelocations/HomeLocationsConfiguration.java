package com.luxoft.homelocations;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jjsanche
 */
public class HomeLocationsConfiguration extends Configuration {
    @JsonProperty("database")
    private final DataSourceFactory database = new DataSourceFactory();
    
    @JsonProperty("httpClient")
    private final HttpClientConfiguration httpClient = new HttpClientConfiguration();
    
    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }
    
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
    
    @JsonProperty("httpClient")
    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }
}