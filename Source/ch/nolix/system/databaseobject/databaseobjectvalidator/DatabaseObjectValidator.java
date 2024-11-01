package ch.nolix.system.databaseobject.databaseobjectvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseobjectapi.databasevalidatorapi.IDatabaseObjectValidator;

public class DatabaseObjectValidator implements IDatabaseObjectValidator {

  @Override
  public final void assertIsLinkedWithRealDatabase(final IDatabaseObject databaseObject) {
    if (!databaseObject.isLinkedWithRealDatabase()) {
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
  public final void assertIsNotLinkedWithRealDatabase(final IDatabaseObject databaseObject) {
    if (databaseObject.isLinkedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is linked with a real database");
    }
  }

  @Override
  public final void assertIsNotNew(final IDatabaseObject databaseObject) {
    if (databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is new");
    }
  }

  @Override
  public final void assertIsOpen(final IDatabaseObject databaseObject) {
    if (databaseObject.isClosed()) {
      throw ClosedArgumentException.forArgument(databaseObject);
    }
  }
}
