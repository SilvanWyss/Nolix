/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.worker;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractBatchWorker extends AbstractWorker {
  /**
   * {@inheritDoc}
   */
  @Override
  protected final void run() {
    while (shouldRunNextStep()) {
      runStep();
    }
  }

  protected abstract void runStep();

  protected abstract boolean shouldRunNextStep();
}
