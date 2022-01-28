//package declaration
package ch.nolix.system.sqlrawobjectschema.schemareader;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.system.sqlrawobjectschema.columntable.ColumnDTOMapper;
import ch.nolix.system.sqlrawobjectschema.structure.TableType;
import ch.nolix.system.sqlrawobjectschema.tabletable.TableDTOMapper;
import ch.nolix.systemapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.SaveStampStrategy;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SchemaReader implements ISchemaReader {
	
	//static attribute
	private static final QueryCreator queryCreator = new QueryCreator();
	
	//static attribute
	private static final TableDTOMapper tableDTOMapper = new TableDTOMapper();
	
	//static attribute
	private static final ColumnDTOMapper columnDTOMapper = new ColumnDTOMapper();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final ISchemaAdapter schemaAdapter;
	
	//constructor
	public SchemaReader(final SQLConnection pSQLConnection,	final ISchemaAdapter schemaAdapter) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(schemaAdapter).thatIsNamed(ISchemaAdapter.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaAdapter = schemaAdapter;
	}
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnName) {
		return schemaAdapter.columnsIsEmpty(TableType.BASE_CONTENT_DATA.getPrefix() + tableName, columnName);
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return
		mSQLConnection
		.getRecords(queryCreator.createQueryToLoadCoumnsByTableId(tableId))
		.to(columnDTOMapper::createColumnDTO);
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumnsByTableName(final String tableName) {
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
	public LinkedList<IFlatTableDTO> loadFlatTables() {
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
	public LinkedList<ITableDTO> loadTables() {
		return loadFlatTables().to(t -> loadTableById(t.getId()));
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
