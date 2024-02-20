//package declaration
package ch.nolix.system.databaseobject.databaseobjectvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseobjectapi.databasevalidatorapi.IDatabaseObjectValidator;

//class
public class DatabaseObjectValidator implements IDatabaseObjectValidator {

  //constant
  private static final DatabaseObjectTool DATABASE_OBJECT_TOOL = new DatabaseObjectTool();

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
    if (!DATABASE_OBJECT_TOOL.isLoaded(databaseObject)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not loaded");
    }
  }

  //method
  @Override
  public final void assertIsNew(final IDatabaseObject databaseObject) {
    if (!DATABASE_OBJECT_TOOL.isNew(databaseObject)) {
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
    if (DATABASE_OBJECT_TOOL.isNew(databaseObject)) {
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
}
