//package declaration
package ch.nolix.system.SQLDatabaseSchemaAdapter;

import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator.Validator;
import ch.nolix.system.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.system.databaseSchemaAdapter.DatabaseState;
import ch.nolix.system.databaseSchemaAdapter.EntitySet;
import ch.nolix.system.databaseSchemaAdapter.IEntitySetAdapter;
import ch.nolix.core.SQL.SQLConnection;
import ch.nolix.core.SQL.SQLDatabaseEngine;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;

//abstract class
public abstract class SQLDatabaseSchemaAdapter<SQLDSA extends SQLDatabaseSchemaAdapter<SQLDSA>>
extends DatabaseSchemaAdapter<SQLDSA> {
	
	//constant
	private static final String DATABASE_PROPERTIES_TABLE_NAME = "DatabaseProperties";
	
	//attribute
	private final SQLConnection SQLConnection;
	
	//constructor
	protected SQLDatabaseSchemaAdapter(final SQLConnection SQLConnection) {
		
		Validator.suppose(SQLConnection).isOfType(SQLConnection.class);
		
		this.SQLConnection = SQLConnection;
	}
	
	//method
	public final DatabaseState getDatabaseState() {
		return
		DatabaseState.valueOf(
			SQLConnection
			.getRows("SELECT content FROM " + DATABASE_PROPERTIES_TABLE_NAME + " WHERE name = 'State'")
			.getRefOne()
			.getRefOne()
		);
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return SQLConnection.getSQLDatabaseEngine();
	}
	
	//method
	@Override
	protected List<IEntitySetAdapter> getEntitySetAdapters() {
		//TODO: Implement.
		return new List<>();
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
			+ "content ntext"
			+ ")",
			getSetDatabaseReadySQLStatement()
		);
	}
	
	//method
	@Override
	protected void lockDatabase() {
		SQLConnection
		.execute(
			"INSERT INTO "
			+ DATABASE_PROPERTIES_TABLE_NAME
			+ " (name, content) VALUES ('State', '"
			+ DatabaseState.Locked.toString()
			+ "')"
		);
	}
	
	//method
	@Override
	protected void saveChangesToDatabaseAndSetDatabaseReady(final IContainer<EntitySet> mutatedEntitySetsInOrder) {
		
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
		
		SQLExecutor.addStatement(getSetDatabaseReadySQLStatement());
		
		SQLExecutor.execute();
	}
	
	//method
	private String getSetDatabaseReadySQLStatement() {
		return
		"INSERT INTO "
		+ DATABASE_PROPERTIES_TABLE_NAME
		+ " (name, content) VALUES ('State', '"
		+ DatabaseState.Locked.toString()
		+ "')";
	}
}
