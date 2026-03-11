package com.github.jonjanisch;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.workflow.WorkflowHandle;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    ExampleWorkflow exampleWorkflow;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var handle = DBOS.startWorkflow(() -> exampleWorkflow.runWorkflow());
        return "Started workflow with ID: " + handle.workflowId();
    }
}
