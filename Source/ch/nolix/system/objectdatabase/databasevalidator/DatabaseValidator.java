//package declaration
package ch.nolix.system.objectdatabase.databasevalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasevalidatorapi.IDatabaseValidator;

//class
public final class DatabaseValidator implements IDatabaseValidator {
	
	//static attribute
	public static final IDatabaseValidator INSTANCE = new DatabaseValidator();
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//constructor
	private DatabaseValidator() {}
	
	//method
	@Override
	public void assertCanSaveChanes(final IDatabase<?> database) {
		if (!databaseHelper.canSaveChanges(database)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(database, "cannot save changes");
		}
	}
}
