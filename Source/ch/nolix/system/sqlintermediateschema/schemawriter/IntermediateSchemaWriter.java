//package declaration
package ch.nolix.system.sqlintermediateschema.schemawriter;

//own imports
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.IIntermediateSchemaWriter;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class IntermediateSchemaWriter implements IIntermediateSchemaWriter {
	
	//attributes
	private final SystemDataWriter systemDataWriter;
	private final SchemaWriter schemaWriter;
	private final SQLConnection mSQLConnection;
	
	//constructor
	public IntermediateSchemaWriter(final SQLConnection pSQLConnection, final ISchemaAdapter schemaAdapter) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		systemDataWriter = new SystemDataWriter();
		schemaWriter = new SchemaWriter(schemaAdapter.getRefSchemaWriter());		
	}
	
	//method
	@Override
	public void addColumn(final String tableName, final IColumnDTO column) {
		systemDataWriter.addColumn(tableName, column);
		schemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		systemDataWriter.addTable(table);
		schemaWriter.addTable(table);
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnHeader) {
		systemDataWriter.deleteColumn(tableName, columnHeader);
		schemaWriter.deleteColumn(tableName, columnHeader);
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		systemDataWriter.deleteTable(tableName);
		schemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return (systemDataWriter.hasChanges() || schemaWriter.hasChanges());
	}
	
	//method
	@Override
	public void saveChanges() {
		
		final ReadContainer<String> lSQLStatements =
		ReadContainer.forIterables(systemDataWriter.getSQLStatements(), schemaWriter.getSQLStatements());
		
		mSQLConnection.execute(lSQLStatements);
	}
	
	//method
	@Override
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		systemDataWriter.setColumnHeader(tableName, columnHeader, newColumnHeader);
		schemaWriter.setColumnHeader(tableName, columnHeader, newColumnHeader);
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
		schemaWriter.setTableName(tableName, newTableName);
	}
}
