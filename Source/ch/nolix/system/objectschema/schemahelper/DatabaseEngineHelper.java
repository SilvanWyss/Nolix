//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseEngineHelper;

//class
public final class DatabaseEngineHelper extends DatabaseObjectHelper implements IDatabaseEngineHelper {
	
	//method
	@Override
	public void assertDoesNotContainDatabaseWithGivenName(
		final IDatabaseEngine databaseEngine,
		final String name
	) {
		if (!containsDatabaseWithGivenName(databaseEngine, name)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains a database with the name '" + name + "'"
			);
		}
	}
	
	//method
	@Override
	public boolean containsDatabaseWithGivenName(
		final IDatabaseEngine databaseEngine,
		final String name
	) {
		return databaseEngine.getOriDatabases().containsAny(db -> db.hasName(name));
	}
	
	//method
	@Override
	public int getDatabaseCount(final IDatabaseEngine databaseEngine) {
		return databaseEngine.getOriDatabases().getElementCount();
	}
	
	//method
	@Override
	public  IDatabase getOriDatabaseWithGivenName(
		final IDatabaseEngine databaseEngine,
		final String name
	) {
		return databaseEngine.getOriDatabases().getOriFirst(db -> db.hasName(name));
	}
}
