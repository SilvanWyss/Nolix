//package declaration
package ch.nolix.system.sqlintermediateschema.schemawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.requestapi.ChangeRequestable;
import ch.nolix.system.sqlintermediateschema.structure.TableType;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;

//class
final class InternalSchemaWriter implements ChangeRequestable {
	
	//static attribute
	private static final SchemaDTOMapper schemaDTOMapper = new SchemaDTOMapper();
	
	//attribute
	private final ISchemaWriter internalSchemaWriter;
	
	//constructor
	public InternalSchemaWriter(final ISchemaWriter internalSchemaWriter) {
		
		Validator.assertThat(internalSchemaWriter).thatIsNamed("internal schema writer").isNotNull();
		
		this.internalSchemaWriter = internalSchemaWriter;
	}
	
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		internalSchemaWriter.addColumn(
			TableType.CONTENT_DATA.getPrefix() + tableName,
			schemaDTOMapper.createSQLColumnDTOFrom(column)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		internalSchemaWriter.addTable(schemaDTOMapper.createSQLTableDTOFrom(table));
	}
	
	//method
	public void deleteColumn(final String tableName, final String columnHeader) {
		internalSchemaWriter.deleteColumn(TableType.CONTENT_DATA.getPrefix() + tableName, columnHeader);
	}
	
	//method
	public void deleteTable(final String tableName) {
		internalSchemaWriter.deleteTable(TableType.CONTENT_DATA.getPrefix() + tableName);
	}
	
	//method
	public IContainer<String> getSQLStatements() {
		return internalSchemaWriter.getSQLStatements();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return internalSchemaWriter.hasChanges();
	}
	
	//method
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		internalSchemaWriter.renameColumn(
			TableType.CONTENT_DATA.getPrefix() + tableName,
			columnHeader,
			newColumnHeader
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		internalSchemaWriter.renameTable(
			TableType.CONTENT_DATA.getPrefix() + tableName,
			TableType.CONTENT_DATA.getPrefix() + newTableName
		);
	}
}
