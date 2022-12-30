//package declaration
package ch.nolix.system.sqlrawdata.databasewriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SQLCollector;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiReferenceStatementCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawdata.sqlapi.IRecordStatementCreator;
import ch.nolix.system.sqlrawdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class InternalDatabaseWriter {
	
	//attribute
	private int saveCount;
	
	//attribute
	private final SQLCollector mSQLCollector = new SQLCollector();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final IRecordStatementCreator recordStatementCreator;
	
	//attribute
	private final IMultiValueStatementCreator multiValueStatementCreator;
	
	//attribute
	private final IMultiReferenceStatementCreator multiReferenceStatementCreator;

	//constructor
	public InternalDatabaseWriter(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		recordStatementCreator = pSQLSyntaxProvider.getRecordStatementCreator();
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
			multiReferenceStatementCreator.createStatementToDeleteEntriesFromMultiReference(entityId, multiReferenceColumnId)
		);
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final String entityId,
		final String multiValueColumnId
	) {
		mSQLCollector.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteEntriesFromMultiValue(entityId, multiValueColumnId)
		);
	}
	
	//method
	public void deleteEntryFromMultiReference(
		final String entityId,
		final String multiReferenceColumnId,
		final String referencedEntityId
	) {
		mSQLCollector.addSQLStatement(
			multiReferenceStatementCreator.createStatementToDeleteEntryFromMultiReference(
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
			multiValueStatementCreator.createStatementToDeleteEntryFromMultiValue(entityId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToDeleteRecordFromTable(tableName, entity)
		);
	}
	
	//method
	public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void expectTableContainsEntity(final String tableName, final String entityId) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToExpectTableContainsEntity(tableName, entityId)
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
	public void insertRecordIntoTable(final String tableName, final INewEntityDTO pRecord) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToInsertRecordIntoTable(tableName, pRecord)
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
		mSQLCollector.addSQLStatement(recordStatementCreator.createStatementToSetEntityAsUpdated(tableName, entity));
	}
	
	//method
	public void updateEntityOnTable(final String tableName, final IEntityUpdateDTO recordUpdate) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToUpdateEntityOnTable(tableName, recordUpdate)
		);
	}
}
