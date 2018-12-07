//package declaration
package ch.nolix.core.SQLDatabaseSchemaAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.databaseSchemaAdapter.EntitySet;
import ch.nolix.core.databaseSchemaAdapter.IEntitySetAdapter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;
import ch.nolix.core.SQL.SQLConnection;
import ch.nolix.core.SQL.SQLDatabaseEngine;

//abstract class
public abstract class SQLDatabaseSchemaAdapter<SQLDSA extends SQLDatabaseSchemaAdapter<SQLDSA>>
extends DatabaseSchemaAdapter<SQLDSA> {
	
	//constant
	private static final String DATABASE_PROPERTIES_TABLE_NAME = "DatabaseProperties";
	
	//attribute
	private final SQLConnection SQLConnection;
	
	//constructor
	protected SQLDatabaseSchemaAdapter(final SQLConnection SQLConnection) {
		
		Validator.suppose(SQLConnection).isInstanceOf(SQLConnection.class);
		
		this.SQLConnection = SQLConnection;
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return SQLConnection.getSQLDatabaseEngine();
	}
	
	//method
	@Override
	public boolean isInitialized() {
		return SQLConnection.tableExistsOnDatabase(DATABASE_PROPERTIES_TABLE_NAME);
	}
	
	//method
	@Override
	protected List<IEntitySetAdapter> getEntitySetAdapters() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	protected void initializeDatabaseWhenNotInitialized() {
		SQLConnection.execute(
			"CREATE TABLE "
			+ DATABASE_PROPERTIES_TABLE_NAME
			+ "("
			+ "id int IDENTITY,"
			+ "name ntext,"
			+ "context ntext"
			+ ")"
		);
	}
	
	//method
	@Override
	protected void saveChangesToDatabase(final IContainer<EntitySet> mutatedEntitySetsInOrder) {
		
		//TODO: Block database for any other mutations as long as this method lasts.
		
		final var createdEntitySets = new List<EntitySet>();
		final var changedEntitySets = new List<EntitySet>();
		final var deletedEntitySets = new List<EntitySet>();
		
		//Iterates the given entity sets.
		for (final var es : mutatedEntitySetsInOrder) {
			
			//Enumerates the state of the current entity set.
			switch (es.getState()) {
				case CREATED:
					createdEntitySets.addAtEnd(es);
					break;
				case CHANGED:
					changedEntitySets.addAtEnd(es);
					break;
				case DELETED:
					deletedEntitySets.addAtEnd(es);
					break;
				default:
					throw new InvalidArgumentException(es, "has the state " + es.getState());
			}
		}
		
		
		final var SQLExecutor = SQLConnection.createSQLExecutor();
		
		for (final var es : createdEntitySets) {
			SQLExecutor.addStatement(es.getSQLHelper(getSQLDatabaseEngine()).getCreateSQLStatement());
		}
		
		//TODO: Handle changed entity sets.
		
		//TODO: Check if all of the given entity sets are allowed to be deleted.
		for (final var es : deletedEntitySets) {
			SQLExecutor.addStatement(es.getSQLHelper(getSQLDatabaseEngine()).getDeleteSQLStatement());
		}
		
		SQLExecutor.execute();
	}
}
