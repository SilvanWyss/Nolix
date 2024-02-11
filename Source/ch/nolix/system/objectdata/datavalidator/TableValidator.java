//package declaration
package ch.nolix.system.objectdata.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.datatool.TableTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.ITableTool;
import ch.nolix.systemapi.objectdataapi.datavalidatorapi.ITableValidator;

//class
public final class TableValidator implements ITableValidator {

  //constant
  private static final ITableTool TABLE_TOOL = new TableTool();

  //method
  @Override
  public void assertCanInsertEntity(final ITable<? extends IEntity> table, final IEntity entity) {
    if (!TABLE_TOOL.canInsertGivenEntity(table, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        entity,
        "cannot be inserted into the table " + table.getNameInQuotes());
    }
  }
}
