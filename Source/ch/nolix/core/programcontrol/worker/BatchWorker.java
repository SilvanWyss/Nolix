package ch.nolix.core.programcontrol.worker;

public abstract class BatchWorker extends Worker {

  @Override
  protected final void run() {
    while (shouldRunNextStep()) {
      runStep();
    }
  }

  protected abstract void runStep();

  protected abstract boolean shouldRunNextStep();
}
