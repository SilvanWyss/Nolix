//package declaration
package ch.nolix.system.sqlrawschema.schemareader;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawschema.columntable.ColumnDTOMapper;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.system.sqlrawschema.tabletable.TableDTOMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.SaveStampStrategy;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SchemaReader implements ISchemaReader {
	
	//static attribute
	private static final QueryCreator queryCreator = new QueryCreator();
	
	//static attribute
	private static final TableDTOMapper tableDTOMapper = new TableDTOMapper();
	
	//static attribute
	private static final ColumnDTOMapper columnDTOMapper = new ColumnDTOMapper();
	
	//static method
	public static SchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final ISchemaAdapter schemaAdapter
	) {
		return new SchemaReader(databaseName, pSQLConnectionPool.borrowSQLConnection(), schemaAdapter);
	}
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final ISchemaAdapter schemaAdapter;
	
	//constructor
	private SchemaReader(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final ISchemaAdapter schemaAdapter
	) {
		
		GlobalValidator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		GlobalValidator.assertThat(schemaAdapter).thatIsNamed(ISchemaAdapter.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaAdapter = schemaAdapter;
		
		createCloseDependencyTo(mSQLConnection);
		createCloseDependencyTo(schemaAdapter);
		
		mSQLConnection.execute("USE " + databaseName);
	}
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnName) {
		return schemaAdapter.columnsIsEmpty(TableType.BASE_CONTENT_DATA.getNamePrefix() + tableName, columnName);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public int getTableCount() {
		return Integer.valueOf(mSQLConnection.getOneRecord(queryCreator.createQueryToGetTableCount()).get(0));
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return
		mSQLConnection
		.getRecords(queryCreator.createQueryToLoadCoumnsByTableId(tableId))
		.to(columnDTOMapper::createColumnDTO);
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumnsByTableName(final String tableName) {
		return
		mSQLConnection
		.getRecords(queryCreator.createQueryToLoadCoumnsByTableName(tableName))
		.to(columnDTOMapper::createColumnDTO);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableById(final String id) {
		return
		tableDTOMapper.createTableDTO(
			mSQLConnection.getOneRecord(queryCreator.createQueryToLoadFlatTableById(id))
		);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableByName(final String name) {
		return
		tableDTOMapper.createTableDTO(
			mSQLConnection.getOneRecord(queryCreator.createQueryToLoadFlatTableByName(name))
		);
	}
	
	//method
	@Override
	public IContainer<IFlatTableDTO> loadFlatTables() {
		return
		mSQLConnection
		.getRecords(queryCreator.createQueryToLoadFlatTables())
		.to(tableDTOMapper::createTableDTO);
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		return
		Time.fromString(
			mSQLConnection.getRecords(queryCreator.createQueryToLoadSchemaTimestamp()).getRefFirst().get(0)
		);
	}
	
	//method
	@Override
	public ITableDTO loadTableById(final String id) {
		return loadTable(loadFlatTableById(id));
	}
	
	//method
	@Override
	public ITableDTO loadTableByName(final String name) {
		return loadTable(loadFlatTableByName(name));
	}
	
	//method
	@Override
	public IContainer<ITableDTO> loadTables() {
		return loadFlatTables().to(t -> loadTableById(t.getId()));
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	private ITableDTO loadTable(final IFlatTableDTO flatTable) {
		return
		new TableDTO(
			flatTable.getId(),
			flatTable.getName(),
			new SaveStampConfigurationDTO(SaveStampStrategy.OWN_SAVE_STAMP),
			loadColumnsByTableId(flatTable.getId())
		);
	}
}
