package com.github.jonjanisch;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.StartWorkflowOptions;
import dev.dbos.transact.workflow.WorkflowHandle;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {

    @Inject
    ExampleWorkflow exampleWorkflow;

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var handle = DBOS.startWorkflow(() -> exampleWorkflow.runWorkflow());
        return "Started workflow with ID: " + handle.workflowId();
    }

    @Path("/queue")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String queue() {
        var handle = DBOS.startWorkflow(() -> exampleWorkflow.runWorkflow(),
                new StartWorkflowOptions().withQueue("exampleQueue"));
        return "Started workflow on exampleQueue with ID: " + handle.workflowId();
    }
}
