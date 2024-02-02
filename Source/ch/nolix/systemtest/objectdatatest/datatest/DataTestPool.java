//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DataTestPool extends TestPool {

  //constructor
  public DataTestPool() {
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
