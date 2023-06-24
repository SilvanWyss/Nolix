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
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISqlSyntaxProvider;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class InternalDatabaseWriter {
	
	//attribute
	private int saveCount;
	
	//attribute
	private final SqlCollector sqlCollector = new SqlCollector();
	
	//attribute
	private final SqlConnection sqlConnection;
	
	//attribute
	private final IEntityStatementCreator entityStatementCreator;
	
	//attribute
	private final IMultiValueStatementCreator multiValueStatementCreator;
	
	//attribute
	private final IMultiReferenceStatementCreator multiReferenceStatementCreator;

	//constructor
	public InternalDatabaseWriter(
		final String databaseName,
		final SqlConnection sqlConnection,
		final ISqlSyntaxProvider sqlSyntaxProvider
	) {
		
		GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();
		
		this.sqlConnection = sqlConnection;
		entityStatementCreator = sqlSyntaxProvider.getEntityStatementCreator();
		multiValueStatementCreator = sqlSyntaxProvider.getMultiValueStatemeentCreator();
		multiReferenceStatementCreator = sqlSyntaxProvider.getMultiReferenceStatemeentCreator();
		
		sqlConnection.execute("USE " + databaseName);
	}
	
	//method
	public void deleteEntriesFromMultiReference(
		final String entityId,
		final String multiReferenceColumnId
	) {
		sqlCollector.addSQLStatement(
			multiReferenceStatementCreator.createStatementToDeleteMultiReferenceEntries(entityId, multiReferenceColumnId)
		);
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final String entityId,
		final String multiValueColumnId
	) {
		sqlCollector.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteMultiValueEntries(entityId, multiValueColumnId)
		);
	}
	
	//method
	public void deleteEntryFromMultiReference(
		final String entityId,
		final String multiReferenceColumnId,
		final String referencedEntityId
	) {
		sqlCollector.addSQLStatement(
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
		sqlCollector.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteMultiValueEntry(entityId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void deleteEntity(final String tableName, final IEntityHeadDTO entity) {
		sqlCollector.addSQLStatement(
			entityStatementCreator.createStatementToDeleteEntity(tableName, entity)
		);
	}
	
	//method
	public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		sqlCollector.addSQLStatement(
			entityStatementCreator.createStatementToExpectGivenSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void expectTableContainsEntity(final String tableName, final String entityId) {
		sqlCollector.addSQLStatement(
			entityStatementCreator.createStatementToExpectTableContainsEntity(tableName, entityId)
		);
	}
	
	//method
	public int getSaveCount() {
		return saveCount;
	}
	
	//method
	public boolean hasChanges() {
		return sqlCollector.containsAny();
	}
	
	//method
	public void insertEntryIntoMultiReference(
		final String entityId,
		final String multiReferenceColumnId,
		final String referencedEntityId
	) {
		sqlCollector.addSQLStatement(
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
		sqlCollector.addSQLStatement(
			multiValueStatementCreator.createQueryToInsertEntryIntoMultiValue(entityId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void insertNewEntity(final String tableName, final INewEntityDTO newEntity) {
		sqlCollector.addSQLStatement(
			entityStatementCreator.createStatementToInsertNewEntity(tableName, newEntity)
		);
	}
	
	//method
	public void reset() {
		sqlCollector.clear();
	}
	
	//method
	public void saveChangesAndReset() {
		try {
			sqlCollector.executeUsingConnection(sqlConnection);
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	public void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		sqlCollector.addSQLStatement(entityStatementCreator.createStatementToSetEntityAsUpdated(tableName, entity));
	}
	
	//method
	public void updateEntityOnTable(final String tableName, final IEntityUpdateDTO entityUpdate) {
		sqlCollector.addSQLStatement(
			entityStatementCreator.createStatementToUpdateEntityOnTable(tableName, entityUpdate)
		);
	}
}
