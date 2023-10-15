//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DatabaseTestPool extends TestPool {

  // constructor
  public DatabaseTestPool() {
    super(
        BackReferenceOnDatabaseTest.class,
        EntityTest.class,
        EntityOnDatabaseTest.class,
        MultiReferenceOnDatabaseTest.class,
        MultiReferenceWithBackReferencesTest.class,
        MultiReferenceWithOptionalBackReferencesTest.class,
        MultiValueOnDatabaseTest.class,
        OptionalBackReferenceOnDatabaseTest.class,
        OptionalReferenceOnDatabaseTest.class,
        OptionalValueTest.class,
        OptionalValueOnDatabaseTest.class,
        ReferenceOnDatabaseTest.class,
        TableOnDatabaseTest.class,
        TableTest.class,
        ValueTest.class,
        ValueOnDatabaseTest.class);
  }
}
