//package declaration
package ch.nolix.core.programcontrol.worker;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;

//class
public abstract class Worker {

  //attribute
  private boolean started;

  //method declaration
  protected abstract void run();

  //method
  protected final void start() {

    setStarted();

    GlobalSequencer.runInBackground(this::run);
  }

  //method
  private void assertIsNotStarted() {
    if (isStarted()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already started");
    }
  }

  //method
  private boolean isStarted() {
    return started;
  }

  //method
  private void setStarted() {

    assertIsNotStarted();

    started = true;
  }
}
