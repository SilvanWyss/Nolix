//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.sqlschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemadto.DataTypeDTO;
import ch.nolix.system.sqlschema.schemadto.TableDTO;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqlschemaapi.schemalanguageapi.ISchemaQueryCreator;

//class
final class SchemaReader implements ISchemaReader {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final ISchemaQueryCreator schemaQueryCreator;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//constructor
	public SchemaReader(final SQLConnection pSQLConnection, final ISchemaQueryCreator schemaQueryCreator) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(schemaQueryCreator).thatIsNamed(ISchemaQueryCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaQueryCreator = schemaQueryCreator;
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
	public void noteClose() {}
	
	//method
	@Override
	public boolean tableExists(String tableName) {
		return
		mSQLConnection
		.getRecords(schemaQueryCreator.createQueryToLoadTable(tableName))
		.containsAny();
	}
}
