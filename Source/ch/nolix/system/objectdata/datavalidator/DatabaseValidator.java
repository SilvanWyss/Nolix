package ch.nolix.system.objectdata.datavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.datavalidatorapi.IDatabaseValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;

public final class DatabaseValidator implements IDatabaseValidator {

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  @Override
  public void assertCanSaveChanges(final IDatabase database) {
    if (!DATABASE_TOOL.canSaveChanges(database)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(database, "cannot save changes");
    }
  }
}
