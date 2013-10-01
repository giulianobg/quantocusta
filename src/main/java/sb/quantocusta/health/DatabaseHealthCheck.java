//package sb.quantocusta.health;
//
//public class DatabaseHealthCheck extends HealthCheck {
//    private final Database database;
//
//    public DatabaseHealthCheck(Database database) {
//        super("database");
//        this.database = database;
//    }
//
//    @Override
//    protected Result check() throws Exception {
//        if (database.isConnected()) {
//            return Result.healthy();
//        } else {
//            return Result.unhealthy("Cannot connect to " + database.getUrl());
//        }
//    }
//}
