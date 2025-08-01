package ch.nolix.system.databaseobject.modelvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidatorUnit;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;

public class DatabaseObjectValidator extends ResourceValidatorUnit implements IDatabaseObjectValidator {

  @Override
  public final void assertIsConnectedWithRealDatabase(final IDatabaseObject databaseObject) {
    if (!databaseObject.isConnectedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not linked with a real database");
    }
  }

  @Override
  public final void assertIsLoaded(final IDatabaseObject databaseObject) {
    if (!databaseObject.isLoaded()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not loaded");
    }
  }

  @Override
  public final void assertIsNew(final IDatabaseObject databaseObject) {
    if (!databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not new");
    }
  }

  @Override
  public final void assertIsNotDeleted(final IDatabaseObject databaseObject) {
    if (databaseObject.isDeleted()) {
      throw DeletedArgumentException.forArgument(databaseObject);
    }
  }

  @Override
  public final void assertIsNotConnectedWithRealDatabase(final IDatabaseObject databaseObject) {
    if (databaseObject.isConnectedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is linked with a real database");
    }
  }

  @Override
  public final void assertIsNotNew(final IDatabaseObject databaseObject) {
    if (databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is new");
    }
  }
}
