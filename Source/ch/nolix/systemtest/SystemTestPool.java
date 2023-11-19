//package declaration
package ch.nolix.systemtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.applicationtest.ApplicationTestPool;
import ch.nolix.systemtest.databaseapplicationtest.DatabaseApplicationTestPool;
import ch.nolix.systemtest.elementtest.ElementTestPool;
import ch.nolix.systemtest.graphictest.GraphicTestPool;
import ch.nolix.systemtest.guitest.GuiTestPool;
import ch.nolix.systemtest.objectdatabasetest.ObjectDatabaseTestPool;
import ch.nolix.systemtest.objectschematest.ObjectSchemaTestPool;
import ch.nolix.systemtest.sqldatabaserawdatatest.SqlDatabaseRawDataTestPool;
import ch.nolix.systemtest.sqlschematest.SqlSchemaTestPool;
import ch.nolix.systemtest.timetest.TimeTestPool;
import ch.nolix.systemtest.webguitest.WebGuiTestPool;

//class
public final class SystemTestPool extends TestPool {

  //constructor
  public SystemTestPool() {
    super(
      new ApplicationTestPool(),
      new DatabaseApplicationTestPool(),
      new ElementTestPool(),
      new GraphicTestPool(),
      new GuiTestPool(),
      new ObjectDatabaseTestPool(),
      new ObjectSchemaTestPool(),
      new SqlSchemaTestPool(),
      new SqlDatabaseRawDataTestPool(),
      new TimeTestPool(),
      new WebGuiTestPool());
  }
}
