//package declaration
package ch.nolix.system.sqldatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.requestuniversalapi.ChangeRequestable;
import ch.nolix.system.sqldatabaserawschema.structure.TableType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaWriter;

//class
final class InternalSchemaWriter implements ChangeRequestable {
	
	//static attribute
	private static final SchemaDTOMapper schemaDTOMapper = new SchemaDTOMapper();
	
	//attribute
	private final ISchemaWriter sqlSchemaWriter;
		
	//constructor
	public InternalSchemaWriter(final ISchemaWriter sqlSchemaWriter) {
		
		GlobalValidator.assertThat(sqlSchemaWriter).thatIsNamed(ISchemaWriter.class).isNotNull();
		
		this.sqlSchemaWriter = sqlSchemaWriter;
	}
	
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		sqlSchemaWriter.addColumn(
			TableType.ENTITY_TABLE.getNamePrefix() + tableName,
			schemaDTOMapper.createSQLColumnDTOFrom(column)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		sqlSchemaWriter.addTable(schemaDTOMapper.createSQLTableDTOFrom(table));
	}
	
	//method
	public void deleteColumn(final String tableName, final String columnName) {
		sqlSchemaWriter.deleteColumn(TableType.ENTITY_TABLE.getNamePrefix() + tableName, columnName);
	}
	
	//method
	public void deleteTable(final String tableName) {
		sqlSchemaWriter.deleteTable(TableType.ENTITY_TABLE.getNamePrefix() + tableName);
	}
	
	//method
	public IContainer<String> getSQLStatements() {
		return sqlSchemaWriter.getSQLStatements();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return sqlSchemaWriter.hasChanges();
	}
	
	//method
	public void reset() {
		sqlSchemaWriter.reset();
	}
	
	//method
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		sqlSchemaWriter.renameColumn(
			TableType.ENTITY_TABLE.getNamePrefix() + tableName,
			columnName,
			newColumnName
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		sqlSchemaWriter.renameTable(
			TableType.ENTITY_TABLE.getNamePrefix() + tableName,
			TableType.ENTITY_TABLE.getNamePrefix() + newTableName
		);
	}
}
