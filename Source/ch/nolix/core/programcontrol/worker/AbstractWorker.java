package ch.nolix.core.programcontrol.worker;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;

public abstract class AbstractWorker {
  private boolean started;

  protected abstract void run();

  protected final void start() {
    setStarted();

    FlowController.runInBackground(this::run);
  }

  private void assertIsNotStarted() {
    if (isStarted()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already started");
    }
  }

  private boolean isStarted() {
    return started;
  }

  private void setStarted() {
    assertIsNotStarted();

    started = true;
  }
}
