//package declaration
package ch.nolix.system.database.databaseobjecthelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;

//class
public class DatabaseObjectHelper implements IDatabaseObjectHelper {

  //method
  @Override
  public final void assertIsLinkedWithRealDatabase(final IDatabaseObject databaseObject) {
    if (!databaseObject.isLinkedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not linked with a real database");
    }
  }

  //method
  @Override
  public final void assertIsLoaded(final IDatabaseObject databaseObject) {
    if (!isLoaded(databaseObject)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not loaded");
    }
  }

  //method
  @Override
  public final void assertIsNew(final IDatabaseObject databaseObject) {
    if (!isNew(databaseObject)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not new");
    }
  }

  //method
  @Override
  public final void assertIsNotDeleted(final IDatabaseObject databaseObject) {
    if (databaseObject.isDeleted()) {
      throw DeletedArgumentException.forArgument(databaseObject);
    }
  }

  //method
  @Override
  public final void assertIsNotLinkedWithRealDatabase(final IDatabaseObject databaseObject) {
    if (databaseObject.isLinkedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is linked with a real database");
    }
  }

  //method
  @Override
  public final void assertIsNotNew(final IDatabaseObject databaseObject) {
    if (isNew(databaseObject)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is new");
    }
  }

  //method
  @Override
  public final void assertIsOpen(final IDatabaseObject databaseObject) {
    if (databaseObject.isClosed()) {
      throw ClosedArgumentException.forArgument(databaseObject);
    }
  }

  //method
  @Override
  public final boolean isDeleted(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.DELETED);
  }

  //method
  @Override
  public final boolean isEdited(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.EDITED);
  }

  //method
  @Override
  public final boolean isLoaded(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.LOADED);
  }

  //method
  @Override
  public final boolean isNew(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.NEW);
  }

  //method
  @Override
  public final boolean isNewOrEdited(final IDatabaseObject databaseObject) {
    return (isNew(databaseObject) || isEdited(databaseObject));
  }

  //method
  @Override
  public boolean isNewOrLoaded(IDatabaseObject databaseObject) {
    return (isNew(databaseObject) || isLoaded(databaseObject));
  }
}
