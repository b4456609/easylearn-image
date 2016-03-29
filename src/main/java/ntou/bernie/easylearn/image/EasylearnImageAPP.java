package ntou.bernie.easylearn.image;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import ntou.bernie.easylearn.image.db.MorphiaService;
import ntou.bernie.easylearn.image.health.DatabaseHealthCheck;
import ntou.bernie.easylearn.image.resources.ImageResources;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by berniefan on 2016/3/29.
 */
public class EasylearnImageAPP extends Application<EasylearnImageAPPConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EasylearnImageAPP.class);

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new EasylearnImageAPP().run(args);

    }

    @Override
    public void run(EasylearnImageAPPConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Application name: {}", configuration.getAppName());
        environment.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");


        // mongodb driver
        MorphiaService morphia = new MorphiaService(configuration.getDatabaseConfiguration());

        ImageResources imageResources = new ImageResources(morphia.getDatastore());
        environment.jersey().register(imageResources);


        // database health check
        environment.healthChecks().register("database",
                new DatabaseHealthCheck(configuration.getDatabaseConfiguration()));

    }
}
