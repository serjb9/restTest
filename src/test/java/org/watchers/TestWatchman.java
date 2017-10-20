package org.watchers;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;

/**
 * Created by serjb on 21.10.2017.
 */
public class TestWatchman extends TestWatcher {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(TestWatchman.class);

    @Override
    protected void failed(Throwable e, Description description) {
        LOG.info("Test "+ description.getDisplayName()+ " - failed.");
    }

    @Override
    protected void succeeded(Description description) {
        LOG.info("Test "+ description.getAnnotations() + " -  successfully completed.");
    }
}
