package com.github.jonjanisch;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.workflow.Workflow;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Typed;
import jakarta.inject.Inject;

// Use @Typed to ensure other resources and services inject
// the registered proxy and not this raw impl.
@Dependent
@Typed(ExampleWorkflowImpl.class)
public class ExampleWorkflowImpl implements ExampleWorkflow {

    @Inject
    ExampleWorkflowSteps steps;

    private ExampleWorkflow proxy;

    public void setProxy(ExampleWorkflow proxy) {
        this.proxy = proxy;
    }

    @Workflow(name = "example-workflow")
    @Override
    public void runWorkflow() {
        DBOS.runStep(() -> steps.stepOne(), "stepOne");
        DBOS.runStep(() -> steps.stepTwo(), "stepTwo");
    }
}
