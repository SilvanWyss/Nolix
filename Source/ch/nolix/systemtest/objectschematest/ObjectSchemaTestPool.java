//package declaration
package ch.nolix.systemtest.objectschematest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.objectschematest.schemaadaptertest.SchemaAdapterTestPool;

//class
public final class ObjectSchemaTestPool extends TestPool {

  // constructor
  public ObjectSchemaTestPool() {
    super(new SchemaAdapterTestPool());
  }
}
