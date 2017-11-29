package org.runners;

import org.listeners.ExecListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;


public class RunnerWithExecListnr extends BlockJUnit4ClassRunner {

    public RunnerWithExecListnr(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addListener(new ExecListener());
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
    }

}
