package org;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Created by serjb on 21.10.2017.
 */
public class BaseTest extends ABase {

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            LOG.info("fail...");
        }

        @Override
        protected void succeeded(Description description) {
            LOG.info("success...");
        }
    };

}
