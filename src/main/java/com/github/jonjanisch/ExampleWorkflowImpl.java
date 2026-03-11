package com.github.jonjanisch;

import com.github.jonjanisch.quarkus.dbos.runtime.DBOSWorkflow;
import dev.dbos.transact.DBOS;
import dev.dbos.transact.workflow.Workflow;
import jakarta.inject.Inject;

@DBOSWorkflow
public class ExampleWorkflowImpl implements ExampleWorkflow {

    @Inject
    ExampleWorkflowSteps steps;

    @Workflow(name = "example-workflow")
    @Override
    public void runWorkflow() {
        DBOS.runStep(() -> steps.stepOne(), "stepOne");
        DBOS.runStep(() -> steps.stepTwo(), "stepTwo");
    }
}
