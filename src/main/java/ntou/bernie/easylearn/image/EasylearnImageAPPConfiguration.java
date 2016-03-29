package ntou.bernie.easylearn.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by berniefan on 2016/3/29.
 */
public class EasylearnImageAPPConfiguration extends Configuration {
    @Valid
    @NotNull
    private String appName;
    @Valid
    @NotNull
    private DatabaseConfiguration databaseConfiguration;

    /**
     * @return the databaseConfiguration
     */
    @JsonProperty("mongoDB")
    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    /**
     * @param databaseConfiguration the databaseConfiguration to set
     */
    @JsonProperty("mongoDB")
    public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    /**
     * @return the appName
     */
    @JsonProperty
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    @JsonProperty
    public void setAppName(String appName) {
        this.appName = appName;
    }

}
