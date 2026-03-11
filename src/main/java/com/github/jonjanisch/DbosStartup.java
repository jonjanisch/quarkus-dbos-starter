package com.github.jonjanisch;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.config.DBOSConfig;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class DbosStartup {
    // Delay the startup of DBOS until producer methods with default priority (2500) are called
    void onStartup(@Observes @Priority(3000) StartupEvent ev) {
        DBOSConfig config = DBOSConfig.defaults("quarkus-dbos-starter")
                .withDatabaseUrl("jdbc:postgresql://localhost:5432/dbos_starter")
                .withDbUser("postgres")
                .withDbPassword("dbos")
                .withAdminServer(false)
                .withDatabaseSchema("dbos_starter")
                .withExecutorId("main-executor");
        DBOS.configure(config);

        DBOS.launch();
    }

    void onShutdown(@Observes ShutdownEvent ev) {
        DBOS.shutdown();
    }
}
