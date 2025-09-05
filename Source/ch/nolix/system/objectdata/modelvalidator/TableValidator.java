package ch.nolix.system.objectdata.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelexaminer.TableExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.ITableExaminer;
import ch.nolix.systemapi.objectdata.modelvalidator.ITableValidator;

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
