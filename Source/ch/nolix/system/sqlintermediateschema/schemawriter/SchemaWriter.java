//package declaration
package ch.nolix.system.sqlintermediateschema.schemawriter;

//own imports
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

//class
public final class SchemaWriter implements ISchemaWriter {
	
	//attributes
	private final SystemDataWriter systemDataWriter;
	private final InternalSchemaWriter internalSchemaWriter;
	private final SQLConnection mSQLConnection;
	
	//constructor
	public SchemaWriter(
		final SQLConnection pSQLConnection,
		final ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaWriter schemaWriter,
		final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO
	) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		systemDataWriter = new SystemDataWriter();
		internalSchemaWriter = new InternalSchemaWriter(schemaWriter, pSQLSaveStampColumnDTO);		
	}
	
	//method
	@Override
	public void addColumn(final String tableName, final IColumnDTO column) {
		systemDataWriter.addColumn(tableName, column);
		internalSchemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		systemDataWriter.addTable(table);
		internalSchemaWriter.addTable(table);
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnHeader) {
		systemDataWriter.deleteColumn(tableName, columnHeader);
		internalSchemaWriter.deleteColumn(tableName, columnHeader);
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		systemDataWriter.deleteTable(tableName);
		internalSchemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return (systemDataWriter.hasChanges() || internalSchemaWriter.hasChanges());
	}
	
	//method
	@Override
	public void saveChanges() {
		
		final ReadContainer<String> lSQLStatements =
		ReadContainer.forIterables(systemDataWriter.getSQLStatements(), internalSchemaWriter.getSQLStatements());
		
		mSQLConnection.execute(lSQLStatements);
	}
	
	//method
	@Override
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		systemDataWriter.setColumnHeader(tableName, columnHeader, newColumnHeader);
		internalSchemaWriter.setColumnHeader(tableName, columnHeader, newColumnHeader);
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String tableName,
		final String columnHeader,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		systemDataWriter.setColumnParametrizedPropertyType(tableName, columnHeader, parametrizedPropertyType);
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		systemDataWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		systemDataWriter.setTableName(tableName, newTableName);
		internalSchemaWriter.setTableName(tableName, newTableName);
	}
}
