package ch.nolix.system.objectdata.datavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelexaminer.TableExaminer;
import ch.nolix.systemapi.objectdataapi.datavalidatorapi.ITableValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.ITableExaminer;

public final class TableValidator implements ITableValidator {

  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  @Override
  public void assertCanInsertEntity(final ITable<? extends IEntity> table, final IEntity entity) {
    if (!TABLE_EXAMINER.canInsertGivenEntity(table, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        entity,
        "cannot be inserted into the table " + table.getNameInQuotes());
    }
  }
}
