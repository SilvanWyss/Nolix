//package declaration
package ch.nolix.system.objectdatabase.databasevalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.databasehelper.TableHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ITableHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasevalidatorapi.ITableValidator;

//class
public final class TableValidator implements ITableValidator {
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//method
	@Override
	public void assertCanInsertGivenEntity(final ITable<?> table, final IEntity entity) {
		if (!tableHelper.canInsertGivenEntity(table, entity)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				entity,
				"cannot be inserted into the table " + table.getNameInQuotes()
			);
		}
	}
}
