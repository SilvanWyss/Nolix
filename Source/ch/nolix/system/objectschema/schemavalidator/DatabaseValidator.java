package ch.nolix.system.objectschema.schemavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schematool.DatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectschemaapi.schemavalidatorapi.IDatabaseValidator;

public final class DatabaseValidator implements IDatabaseValidator {

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  @Override
  public void assertContainsTable(final IDatabase database, final ITable table) {
    if (!DATABASE_TOOL.containsGivenTable(database, table)) {
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
