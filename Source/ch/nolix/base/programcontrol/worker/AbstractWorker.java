/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.worker;

import ch.nolix.base.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;

/**
 * @author Silvan Wyss
 */
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
