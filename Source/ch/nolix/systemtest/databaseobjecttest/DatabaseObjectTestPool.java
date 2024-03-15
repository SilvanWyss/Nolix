//package declaration
package ch.nolix.systemtest.databaseobjecttest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.databaseobjecttest.databaseobjecttooltest.DatabaseObjectToolTestPool;

//class
public final class DatabaseObjectTestPool extends TestPool {

  //constructor
  public DatabaseObjectTestPool() {
    super(new DatabaseObjectToolTestPool());
  }
}
