//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IDatabaseEngineHelper;

//class
public final class DatabaseEngineHelper extends DatabaseObjectHelper implements IDatabaseEngineHelper {
	
	//method
	@Override
	public void assertDoesNotContainDatabaseWithGivenName(
		final IDatabaseEngine databaseEngine,
		final String name
	) {
		if (!containsDatabaseWithGivenName(databaseEngine, name)) {
			throw new InvalidArgumentException(this, "contains a database with the name '" + name + "'");
		}
	}
	
	//method
	@Override
	public boolean containsDatabaseWithGivenName(
		final IDatabaseEngine databaseEngine,
		final String name
	) {
		return databaseEngine.getRefDatabases().containsAny(db -> db.hasName(name));
	}
	
	//method
	@Override
	public int getDatabaseCount(final IDatabaseEngine databaseEngine) {
		return databaseEngine.getRefDatabases().getElementCount();
	}
	
	//method
	@Override
	public IDatabase getRefDatabaseWithGivenName(
		final IDatabaseEngine databaseEngine,
		final String name
	) {
		return databaseEngine.getRefDatabases().getRefFirst(db -> db.hasName(name));
	}
}
