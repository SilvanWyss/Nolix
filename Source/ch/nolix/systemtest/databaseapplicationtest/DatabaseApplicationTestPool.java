//package declaration
package ch.nolix.systemtest.databaseapplicationtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.databaseapplicationtest.propertybindertest.PropertyBinderTestPool;

//class
public final class DatabaseApplicationTestPool extends TestPool {

  //constructor
  public DatabaseApplicationTestPool() {
    super(new PropertyBinderTestPool());
  }
}
