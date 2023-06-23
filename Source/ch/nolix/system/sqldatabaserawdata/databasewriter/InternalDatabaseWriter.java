//package declaration
package ch.nolix.system.sqldatabaserawdata.databasewriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class InternalDatabaseWriter {
	
	//attribute
	private int saveCount;
	
	//attribute
	private final SqlCollector mSQLCollector = new SqlCollector();
	
	//attribute
	private final SqlConnection mSQLConnection;
	
	//attribute
	private final IEntityStatementCreator entityStatementCreator;
	
	//attribute
	private final IMultiValueStatementCreator multiValueStatementCreator;
	
	//attribute
	private final IMultiReferenceStatementCreator multiReferenceStatementCreator;

	//constructor
	public InternalDatabaseWriter(
		final String databaseName,
		final SqlConnection pSQLConnection,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SqlConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		entityStatementCreator = pSQLSyntaxProvider.getEntityStatementCreator();
		multiValueStatementCreator = pSQLSyntaxProvider.getMultiValueStatemeentCreator();
		multiReferenceStatementCreator = pSQLSyntaxProvider.getMultiReferenceStatemeentCreator();
		
		pSQLConnection.execute("USE " + databaseName);
	}
	
	//method
	public void deleteEntriesFromMultiReference(
		final String entityId,
		final String multiReferenceColumnId
	) {
		mSQLCollector.addSQLStatement(
			multiReferenceStatementCreator.createStatementToDeleteMultiReferenceEntries(entityId, multiReferenceColumnId)
		);
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final String entityId,
		final String multiValueColumnId
	) {
		mSQLCollector.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId)
		);
	}
	
	//method
	public void deleteEntryFromMultiReference(
		final String entityId,
		final String multiReferenceColumnId,
		final String referencedEntityId
	) {
		mSQLCollector.addSQLStatement(
			multiReferenceStatementCreator.createStatementToDeleteMultiReferenceEntry(
				entityId,
				multiReferenceColumnId,
				referencedEntityId
			)
		);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final String entityId,
		final String multiValueColumnId,
		final String entry
	) {
		mSQLCollector.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void deleteEntity(final String tableName, final IEntityHeadDTO entity) {
		mSQLCollector.addSQLStatement(
			entityStatementCreator.createStatementToDeleteEntity(tableName, entity)
		);
	}
	
	//method
	public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		mSQLCollector.addSQLStatement(
			entityStatementCreator.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void expectTableContainsEntity(final String tableName, final String entityId) {
		mSQLCollector.addSQLStatement(
			entityStatementCreator.createStatementToExpectTableContainsEntity(tableName, entityId)
		);
	}
	
	//method
	public int getSaveCount() {
		return saveCount;
	}
	
	//method
	public boolean hasChanges() {
		return mSQLCollector.containsAny();
	}
	
	//method
	public void insertEntryIntoMultiReference(
		final String entityId,
		final String multiReferenceColumnId,
		final String referencedEntityId
	) {
		mSQLCollector.addSQLStatement(
			multiReferenceStatementCreator.createStatementToInsertEntryIntoMultiReference(
				entityId,
				multiReferenceColumnId,
				referencedEntityId
			)
		);
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final String entityId,
		final String multiValueColumnId,
		final String entry
	) {
		mSQLCollector.addSQLStatement(
			multiValueStatementCreator.createQueryToInsertEntryIntoMultiValue(entityId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void insertNewEntity(final String tableName, final INewEntityDTO newEntity) {
		mSQLCollector.addSQLStatement(
			entityStatementCreator.createStatementToInsertNewEntity(tableName, newEntity)
		);
	}
	
	//method
	public void reset() {
		mSQLCollector.clear();
	}
	
	//method
	public void saveChangesAndReset() {
		try {
			mSQLCollector.executeUsingConnection(mSQLConnection);
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	public void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		mSQLCollector.addSQLStatement(entityStatementCreator.createStatementToSetEntityAsUpdated(tableName, entity));
	}
	
	//method
	public void updateEntityOnTable(final String tableName, final IEntityUpdateDTO entityUpdate) {
		mSQLCollector.addSQLStatement(
			entityStatementCreator.createStatementToUpdateEntityOnTable(tableName, entityUpdate)
		);
	}
}
