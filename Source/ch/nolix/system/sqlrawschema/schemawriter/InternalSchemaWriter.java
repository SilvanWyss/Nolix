//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.requestuniversalapi.ChangeRequestable;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaWriter;

//class
final class InternalSchemaWriter implements ChangeRequestable {
	
	//static attribute
	private static final SchemaDTOMapper schemaDTOMapper = new SchemaDTOMapper();
	
	//attribute
	private final ISchemaWriter mSQLSchemaWriter;
		
	//constructor
	public InternalSchemaWriter(final ISchemaWriter pSQLSchemaWriter) {
		
		GlobalValidator.assertThat(pSQLSchemaWriter).thatIsNamed(ISchemaWriter.class).isNotNull();
		
		this.mSQLSchemaWriter = pSQLSchemaWriter;
	}
	
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		mSQLSchemaWriter.addColumn(
			TableType.BASE_CONTENT_DATA.getNamePrefix() + tableName,
			schemaDTOMapper.createSQLColumnDTOFrom(column)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		mSQLSchemaWriter.addTable(schemaDTOMapper.createSQLTableDTOFrom(table));
	}
	
	//method
	public void deleteColumn(final String tableName, final String columnName) {
		mSQLSchemaWriter.deleteColumn(TableType.BASE_CONTENT_DATA.getNamePrefix() + tableName, columnName);
	}
	
	//method
	public void deleteTable(final String tableName) {
		mSQLSchemaWriter.deleteTable(TableType.BASE_CONTENT_DATA.getNamePrefix() + tableName);
	}
	
	//method
	public IContainer<String> getSQLStatements() {
		return mSQLSchemaWriter.getSQLStatements();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLSchemaWriter.hasChanges();
	}
	
	//method
	public void reset() {
		mSQLSchemaWriter.reset();
	}
	
	//method
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		mSQLSchemaWriter.renameColumn(
			TableType.BASE_CONTENT_DATA.getNamePrefix() + tableName,
			columnName,
			newColumnName
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		mSQLSchemaWriter.renameTable(
			TableType.BASE_CONTENT_DATA.getNamePrefix() + tableName,
			TableType.BASE_CONTENT_DATA.getNamePrefix() + newTableName
		);
	}
}
