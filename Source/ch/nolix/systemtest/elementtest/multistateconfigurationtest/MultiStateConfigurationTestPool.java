//package declaration
package ch.nolix.systemtest.elementtest.multistateconfigurationtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MultiStateConfigurationTestPool extends TestPool {

  //constructor
  public MultiStateConfigurationTestPool() {
    super(
      CascadingPropertyInMultiStateConfigurationTest.class,
      MultiStateConfigurationWithNonCascadingPropertyTest.class);
  }
}
