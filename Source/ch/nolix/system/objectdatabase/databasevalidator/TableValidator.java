//package declaration
package ch.nolix.system.objectdatabase.databasevalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.databasetool.TableTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasetoolapi.ITableTool;
import ch.nolix.systemapi.objectdatabaseapi.databasevalidatorapi.ITableValidator;

//class
public final class TableValidator implements ITableValidator {

  //constant
  private static final ITableTool TABLE_HELPER = new TableTool();

  //method
  @Override
  public void assertCanInsertGivenEntity(final ITable<?> table, final IEntity entity) {
    if (!TABLE_HELPER.canInsertGivenEntity(table, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        entity,
        "cannot be inserted into the table " + table.getNameInQuotes());
    }
  }
}
