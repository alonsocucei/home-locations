package com.luxoft.homelocations;

import com.luxoft.homelocations.representations.Home;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.assertj.core.api.Assertions.assertThat;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;
import org.junit.Before;
import org.junit.ClassRule;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Client client;
    private Home invalidHome = new Home(0, "Test address", "Test floor", "Test state", "Test city", "Test-zipcode", "Test country");
    private Home validHome = new Home(0, "Test address", "Test floor", "Test state", "Test city", "44123", "Mexico");

    @ClassRule
    public static final DropwizardAppRule<HomeLocationsConfiguration> RULE
            = new DropwizardAppRule<>(App.class, "config.yml");

    @Before
    public void setUp() {
        client = new JerseyClientBuilder().build();
    }

    @Test
    public void getHomeTest() {
        Home result = client.target(
                String.format("http://localhost:%d/api/homes", RULE.getLocalPort())
        ).path("1").request().get(Home.class);
        System.out.println(result.getAddress1());
        assertThat(result).isNotNull();
    }
    
    @Test
    public void createInvalidHomeTest() {
        Response response = client.target(
                String.format("http://localhost:%d/api/homes", RULE.getLocalPort())
        ).request().buildPost(Entity.entity(invalidHome, MediaType.APPLICATION_JSON_TYPE)).invoke();
        
        assertThat(response.getStatus()).isEqualTo(400);
    }
    
    @Test
    public void createValidHomeTest() {
        Response response = client.target(
                String.format("http://localhost:%d/api/homes", RULE.getLocalPort())
        ).request().buildPost(Entity.entity(validHome, MediaType.APPLICATION_JSON_TYPE)).invoke();
        
        assertThat(response.getStatus()).isEqualTo(201);
    }
}
