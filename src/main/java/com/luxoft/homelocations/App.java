package com.luxoft.homelocations;

import com.luxoft.homelocations.resources.HomeResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Hello world!
 *
 */
public class App extends Application<HomeLocationsConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )throws Exception {
        new App().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<HomeLocationsConfiguration> b) {
        b.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }
    
    @Override
    public void run(HomeLocationsConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "mysql");
        e.jersey().register(new HomeResource(jdbi, e.getValidator()));
    }
}
