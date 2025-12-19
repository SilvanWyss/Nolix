package ch.nolix.system.objectdata.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.modelexaminer.DatabaseExaminer;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.modelexaminer.IDatabaseExaminer;
import ch.nolix.systemapi.objectdata.modelvalidator.IDatabaseValidator;

/**
 * @author Silvan Wyss
 */
public final class DatabaseValidator implements IDatabaseValidator {
  private static final IDatabaseExaminer DATABASE_EXAMINER = new DatabaseExaminer();

  @Override
  public void assertCanSaveChanges(final IDatabase database) {
    if (!DATABASE_EXAMINER.canSaveChanges(database)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(database, "cannot save changes");
    }
  }
}
