//package declaration
package ch.nolix.core.programcontrol.worker;

//class
public abstract class BatchWorker extends Worker {

  //method
  @Override
  protected final void run() {
    while (shouldRunNextStep()) {
      runStep();
    }
  }

  //method declaration
  protected abstract void runStep();

  //method declaration
  protected abstract boolean shouldRunNextStep();
}
