//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MsSqlSchemaQueryCreator;
import ch.nolix.system.sqldatabasebasicschema.mssqllanguage.MsSqlSchemaStatementCreator;

//class
public final class MsSqlSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
		final String databaseName,	
		final SqlConnectionPool sqlConnectionPool
	) {
		return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
	}
	
	//constructor
	private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
		super(databaseName, sqlConnectionPool, new MsSqlSchemaQueryCreator(), new MsSqlSchemaStatementCreator());
	}
}
