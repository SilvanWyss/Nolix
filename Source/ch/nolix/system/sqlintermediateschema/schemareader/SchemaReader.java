//package declaration
package ch.nolix.system.sqlintermediateschema.schemareader;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlintermediateschema.columnsystemtable.ColumnDTOMapper;
import ch.nolix.system.sqlintermediateschema.structure.TableType;
import ch.nolix.system.sqlintermediateschema.tablesystemtable.TableDTOMapper;
import ch.nolix.techapi.intermediateschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;

//class
public final class SchemaReader implements ISchemaReader {
	
	//static attributes
	private static final QueryCreator queryCreator = new QueryCreator();
	private static final TableDTOMapper tableDTOMapper = new TableDTOMapper();
	private static final ColumnDTOMapper columnDTOMapper = new ColumnDTOMapper();
	
	//attributes
	private final SQLConnection mSQLConnection;
	private final ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaReader schemaReader;
	
	//constructor
	public SchemaReader(
		final SQLConnection pSQLConnection,
		final ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaReader schemaReader
	) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(schemaReader).thatIsNamed(ISchemaReader.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaReader = schemaReader;
	}
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnHeader) {
		return schemaReader.columnsIsEmpty(TableType.CONTENT_DATA.getPrefix() + tableName, columnHeader);
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
