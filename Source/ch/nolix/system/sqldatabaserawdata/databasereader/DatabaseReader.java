//package declaration
package ch.nolix.system.sqldatabaserawdata.databasereader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseReader;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISqlSyntaxProvider;

//class
public final class DatabaseReader implements IDatabaseReader {
	
	//static method
	public static DatabaseReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndTableInfosAndSQLSyntaxProvider(
		final String databaseName,
		final SqlConnectionPool sqlConnectionPool,
		final IContainer<ITableInfo> tableInfos,
		final ISqlSyntaxProvider sqlSyntaxProvider
	) {
		return
		new DatabaseReader(databaseName, sqlConnectionPool.borrowSQLConnection(), tableInfos, sqlSyntaxProvider);
	}
		
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final InternalDatabaseReader internalDatabaseReader;
	
	//multi-attribute
	private final IContainer<ITableInfo> tableInfos;
	
	//constructor
	private DatabaseReader(
		final String databaseName,
		final SqlConnection sqlConnection,
		final IContainer<ITableInfo> tableInfos,
		final ISqlSyntaxProvider sqlSyntaxProvider
	) {
		
		GlobalValidator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDatabaseReader = new InternalDatabaseReader(databaseName, sqlConnection, sqlSyntaxProvider);
		this.tableInfos = tableInfos;
		
		createCloseDependencyTo(sqlConnection);
	}
	
	//method
	@Override
	public CloseController getOriCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public Time getSchemaTimestamp() {
		return internalDatabaseReader.getSchemaTimestamp();
	}
	
	//method
	@Override
	public IContainer<String> loadMultiReferenceEntries(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return internalDatabaseReader.loadMultiReferenceEntries(
			entityId,
			getColumnInfoByTableNameAndColumnName(tableName, multiReferenceColumnName)
		);
	}
	
	//method
	@Override
	public IContainer<Object> loadMultiValueEntries(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		return
		internalDatabaseReader.loadMultiValueEntries(
			entityId,
			getColumnInfoByTableNameAndColumnName(tableName, multiValueColumnName)
		);
	}
	
	//method
	@Override
	public IContainer<ILoadedEntityDTO> loadEntitiesOfTable(final String tableName) {
		return internalDatabaseReader.loadEntitiesOfTable(getTableInfoByTableName(tableName));
	}

	//method
	@Override
	public ILoadedEntityDTO loadEntity(final String tableName, final String id) {
		return internalDatabaseReader.loadEntity(getTableInfoByTableName(tableName), id);
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		
		final var columnInfo = getColumnInfoByTableNameAndColumnName(tableName, columnName);
		
		return internalDatabaseReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnInfo, value);
	}
	
	//method
	@Override
	public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
		return internalDatabaseReader.tableContainsEntityWithGivenId(tableName, id);
	}
	
	//method
	private IColumnInfo getColumnInfoByTableNameAndColumnName(
		final String tableName,
		final String columnName
	) {
		return getTableInfoByTableName(tableName).getColumnInfoByColumnName(columnName);
	}
	
	//method
	private ITableInfo getTableInfoByTableName(final String tableName) {
		return tableInfos.getOriFirstOrNull(td -> td.getTableName().equals(tableName));
	}
}
