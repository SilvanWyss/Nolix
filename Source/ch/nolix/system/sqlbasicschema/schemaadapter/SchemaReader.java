//package declaration
package ch.nolix.system.sqlbasicschema.schemaadapter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.sqlbasicschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.sqlbasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlbasicschema.schemadto.DataTypeDTO;
import ch.nolix.system.sqlbasicschema.schemadto.TableDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemalanguageapi.ISchemaQueryCreator;

//class
final class SchemaReader implements ISchemaReader {
	
	//static method
	public static SchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaQueryCreator(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final ISchemaQueryCreator schemaQueryCreator
	) {
		return new SchemaReader(databaseName, pSQLConnectionPool.borrowSQLConnection(), schemaQueryCreator);
	}
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final ISchemaQueryCreator schemaQueryCreator;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//constructor
	private SchemaReader(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final ISchemaQueryCreator schemaQueryCreator
	) {
		
		GlobalValidator.assertThat(schemaQueryCreator).thatIsNamed(ISchemaQueryCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaQueryCreator = schemaQueryCreator;
		
		createCloseDependencyTo(mSQLConnection);
		mSQLConnection.execute("USE " + databaseName);
	}
	
	//method
	@Override
	public boolean columnsIsEmpty(final String tableName, final String columnName) {
		return
		mSQLConnection
		.getRecords(schemaQueryCreator.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName))
		.isEmpty();
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return
		mSQLConnection
		.getRecords(schemaQueryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName))
		.to(r -> new ColumnDTO(r.get(0), new DataTypeDTO(r.get(1))));
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return
		mSQLConnection
		.getRecordsAsStrings(schemaQueryCreator.createQueryToLoadNameOfTables())
		.to(FlatTableDTO::new);
	}
	
	//method
	@Override
	public LinkedList<ITableDTO> loadTables() {
		return loadFlatTables().to(t -> new TableDTO(t.getName(), loadColumns(t.getName())));
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public boolean tableExists(String tableName) {
		return
		mSQLConnection
		.getRecords(schemaQueryCreator.createQueryToLoadTable(tableName))
		.containsAny();
	}
}
