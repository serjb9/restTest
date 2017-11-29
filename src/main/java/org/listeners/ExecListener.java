package org.listeners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;

@RunListener.ThreadSafe
public class ExecListener extends RunListener{

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ExecListener.class);
    private Description desc;

    @Override
    public void testRunStarted(Description desc) throws Exception {
        this.desc = desc;
        LOG.info("Scope "+ desc.getDisplayName() + " - STARTED.");
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        LOG.warn("Scope: " + desc.getDisplayName());
        LOG.warn("Success?: " + result.wasSuccessful());
        LOG.warn("Taken time: " + result.getRunTime()+"ms - "+  (float) result.getRunTime()/1000.0 + " sec");
        LOG.warn("Failures: " + result.getFailureCount());
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        LOG.warn("Test failed: "+ failure.getMessage());
    }
}
