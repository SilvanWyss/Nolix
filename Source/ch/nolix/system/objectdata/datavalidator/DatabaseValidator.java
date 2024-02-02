//package declaration
package ch.nolix.system.objectdata.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.datavalidatorapi.IDatabaseValidator;

//class
public final class DatabaseValidator implements IDatabaseValidator {

  //constant
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //method
  @Override
  public void assertCanSaveChanges(final IDatabase database) {
    if (!DATABASE_TOOL.canSaveChanges(database)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(database, "cannot save changes");
    }
  }
}
