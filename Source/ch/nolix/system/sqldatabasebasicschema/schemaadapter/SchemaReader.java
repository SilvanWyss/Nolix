//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqldatabasebasicschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDTO;
import ch.nolix.system.sqldatabasebasicschema.schemadto.DataTypeDTO;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemalanguageapi.ISchemaQueryCreator;

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
	private final SqlConnection mSQLConnection;
	
	//attribute
	private final ISchemaQueryCreator schemaQueryCreator;
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//constructor
	private SchemaReader(
		final String databaseName,
		final SqlConnection pSQLConnection,
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
	public CloseController getOriCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumns(final String tableName) {
		return
		mSQLConnection
		.getRecords(schemaQueryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName))
		.to(r -> new ColumnDTO(r.get(0), new DataTypeDTO(r.get(1))));
	}
	
	//method
	@Override
	public IContainer<IFlatTableDTO> loadFlatTables() {
		return
		mSQLConnection
		.getRecordsAsStrings(schemaQueryCreator.createQueryToLoadNameOfTables())
		.to(FlatTableDTO::new);
	}
	
	//method
	@Override
	public IContainer<ITableDTO> loadTables() {
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
