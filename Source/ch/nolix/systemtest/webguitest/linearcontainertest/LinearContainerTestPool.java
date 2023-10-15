//package declaration
package ch.nolix.systemtest.webguitest.linearcontainertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class LinearContainerTestPool extends TestPool {

  // constructor
  public LinearContainerTestPool() {
    super(HorizontalStackHtmlBuilderTest.class, HorizontalStackTest.class);
  }
}
