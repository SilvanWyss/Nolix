/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.programcontrol.worker;

public abstract class AbstractBatchWorker extends AbstractWorker {
  @Override
  protected final void run() {
    while (shouldRunNextStep()) {
      runStep();
    }
  }

  protected abstract void runStep();

  protected abstract boolean shouldRunNextStep();
}
