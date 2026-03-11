package com.github.jonjanisch;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleWorkflowSteps {

    public void stepOne() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.error("stepOne interrupted", e);
        }
        Log.info("Completed step 1!");
    }

    public void stepTwo() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.error("stepTwo interrupted", e);
        }
        Log.info("Completed step 2!");
    }
}
