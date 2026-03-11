package com.github.jonjanisch;

import dev.dbos.transact.DBOS;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class WorkflowProducers {

    // Use @Startup to ensure this is eagerly initialized
    // Otherwise, it will be initialized after DBOS.launch()
    @Startup
    @Produces
    @ApplicationScoped
    public ExampleWorkflow produceExampleWorkflow(ExampleWorkflowImpl impl) {
        ExampleWorkflow proxy = DBOS.registerWorkflows(ExampleWorkflow.class, impl);
        impl.setProxy(proxy);
        return proxy;
    }
}
