//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class AtomicControlTestPool extends TestPool {

  //constructor
  public AtomicControlTestPool() {
    super(
      ButtonCssBuilderTest.class,
      ButtonHtmlBuilderTest.class,
      ButtonTest.class,
      ImageControlTest.class,
      LinkTest.class,
      LabelTest.class,
      TextboxTest.class,
      TextboxCssBuilderTest.class,
      UploaderTest.class,
      ValidationLabelTest.class);
  }
}
