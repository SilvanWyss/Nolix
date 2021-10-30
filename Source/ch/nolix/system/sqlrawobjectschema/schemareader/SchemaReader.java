//package declaration
package ch.nolix.system.sqlrawobjectschema.schemareader;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectschema.columnsystemtable.ColumnDTOMapper;
import ch.nolix.system.sqlrawobjectschema.structure.TableType;
import ch.nolix.system.sqlrawobjectschema.tablesystemtable.TableDTOMapper;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SchemaReader implements ISchemaReader {
	
	//static attributes
	private static final QueryCreator queryCreator = new QueryCreator();
	private static final TableDTOMapper tableDTOMapper = new TableDTOMapper();
	private static final ColumnDTOMapper columnDTOMapper = new ColumnDTOMapper();
	
	//attributes
	private final SQLConnection mSQLConnection;
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
	public boolean columnIsEmpty(final String tableName, final String columnHeader) {
		return schemaAdapter.columnsIsEmpty(TableType.CONTENT_DATA.getPrefix() + tableName, columnHeader);
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return
		mSQLConnection
		.getRecords(queryCreator.createQueryToLoadCoumns(tableName))
		.to(columnDTOMapper::createColumnDTO);
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
}
