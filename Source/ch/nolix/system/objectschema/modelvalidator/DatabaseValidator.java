package ch.nolix.system.objectschema.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.modelexaminer.DatabaseExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IDatabaseExaminer;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.IDatabaseValidator;

public final class DatabaseValidator implements IDatabaseValidator {

  private static final IDatabaseExaminer DATABASE_EXAMINER = new DatabaseExaminer();

  @Override
  public void assertContainsTable(final IDatabase database, final ITable table) {
    if (!DATABASE_EXAMINER.containsTable(database, table)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(database, table);
    }
  }

  @Override
  public void assertContainsTables(final IDatabase database, final IContainer<ITable> tables) {
    for (final var t : tables) {
      assertContainsTable(database, t);
    }
  }
}
