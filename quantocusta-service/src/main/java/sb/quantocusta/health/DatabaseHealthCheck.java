package sb.quantocusta.health;

import org.h2.engine.Database;

import com.yammer.metrics.core.HealthCheck;
import com.yammer.metrics.core.HealthCheck.Result;

public class DatabaseHealthCheck extends HealthCheck {
    private final Database database;

    public DatabaseHealthCheck(Database database) {
        super("database");
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
//        if (database.isConnected()) {
            return Result.healthy();
//        } else {
//            return Result.unhealthy("Cannot connect to " + database.getUrl());
//        }
    }
}
