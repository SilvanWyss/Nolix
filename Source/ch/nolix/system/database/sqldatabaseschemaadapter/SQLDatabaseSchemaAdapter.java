//package declaration
package ch.nolix.system.database.sqldatabaseschemaadapter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.common.sql.SQLDatabaseEngine;
import ch.nolix.system.database.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.database.databaseschemaadapter.DatabaseState;
import ch.nolix.system.database.databaseschemaadapter.EntitySet;
import ch.nolix.system.database.databaseschemaadapter.EntitySetMSSQLHelper;
import ch.nolix.system.database.databaseschemaadapter.IEntitySetAdapter;

//class
public abstract class SQLDatabaseSchemaAdapter<SQLDSA extends SQLDatabaseSchemaAdapter<SQLDSA>>
extends DatabaseSchemaAdapter<SQLDSA> {
	
	//constant
	private static final String DATABASE_PROPERTIES_TABLE_NAME = "DatabaseProperties";
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//constructor
	protected SQLDatabaseSchemaAdapter(final SQLConnection pSQLConnection) {
		
		Validator.assertThat(pSQLConnection).isOfType(SQLConnection.class);
		
		this.mSQLConnection = pSQLConnection;
	}
	
	//method
	public final DatabaseState getDatabaseState() {
		return
		DatabaseState.valueOf(
			mSQLConnection
			.getRows("SELECT content FROM " + DATABASE_PROPERTIES_TABLE_NAME + " WHERE name = 'State'")
			.getRefOne()
			.getRefOne()
		);
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return mSQLConnection.getSQLDatabaseEngine();
	}
	
	//method
	@Override
	protected LinkedList<IEntitySetAdapter> getEntitySetAdapters() {
		//TODO: Implement.
		return new LinkedList<>();
	}
	
	//method
	@Override
	protected void initializeDatabaseWhenNotInitialized() {
		mSQLConnection.execute(
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
		mSQLConnection
		.execute(
			"INSERT INTO "
			+ DATABASE_PROPERTIES_TABLE_NAME
			+ " (name, content) VALUES ('State', '"
			+ DatabaseState.LOCKED.toString()
			+ "')"
		);
	}
	
	//method
	@Override
	protected void saveAddEntitySet(final EntitySet entitySet) {
		mSQLConnection.execute(createAddEntitySetSQLStatementFor(entitySet));
	}
	
	//method
	@Override
	protected void saveChangesToDatabaseAndSetDatabaseReady(final IContainer<EntitySet> mutatedEntitySetsInOrder) {
		
		final var createdEntitySets = new LinkedList<EntitySet>();
		final var changedEntitySets = new LinkedList<EntitySet>();
		final var deletedEntitySets = new LinkedList<EntitySet>();
		
		//Iterates the given entity sets.
		for (final var es : mutatedEntitySetsInOrder) {
			
			//Enumerates the state of the current entity set.
			switch (es.getState()) {
				case NEW:
					createdEntitySets.addAtEnd(es);
					break;
				case EDITED:
					changedEntitySets.addAtEnd(es);
					break;
				case DELETED:
					deletedEntitySets.addAtEnd(es);
					break;
				default:
					throw new InvalidArgumentException(es, "has the state " + es.getState());
			}
		}
		
		final var lSQLExecutor = mSQLConnection.createSQLExecutor();
		
		for (final var es : createdEntitySets) {
			lSQLExecutor.addStatement(es.getSQLHelper(getSQLDatabaseEngine()).getCreateSQLStatement());
		}
		
		//TODO: Handle changedEntitySets.
		
		//TODO: Check if all of the given deletedEntitySets are allowed to be deleted.
		for (final var es : deletedEntitySets) {
			lSQLExecutor.addStatement(es.getSQLHelper(getSQLDatabaseEngine()).getDeleteSQLStatement());
		}
		
		lSQLExecutor.addStatement(getSetDatabaseReadySQLStatement());
		
		lSQLExecutor.execute();
	}
	
	//method
	private String createAddEntitySetSQLStatementFor(EntitySet entitySet) {
		return new EntitySetMSSQLHelper(entitySet).getCreateSQLStatement();
	}
	
	//method
	private String getSetDatabaseReadySQLStatement() {
		return
		"INSERT INTO "
		+ DATABASE_PROPERTIES_TABLE_NAME
		+ " (name, content) VALUES ('State', '"
		+ DatabaseState.LOCKED.toString()
		+ "')";
	}
}
