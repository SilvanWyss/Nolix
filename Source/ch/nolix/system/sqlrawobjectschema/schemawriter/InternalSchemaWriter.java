//package declaration
package ch.nolix.system.sqlrawobjectschema.schemawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.requestapi.ChangeRequestable;
import ch.nolix.system.sqlrawobjectschema.structure.TableType;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;

//class
final class InternalSchemaWriter implements ChangeRequestable {
	
	//attributes
	private final ISchemaWriter mSQLSchemaWriter;
	private final SchemaDTOMapper schemaDTOMapper;
	
	//constructor
	public InternalSchemaWriter(
		final ISchemaWriter pSQLSchemaWriter,
		final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO
	) {
		
		Validator.assertThat(pSQLSchemaWriter).thatIsNamed(ISchemaWriter.class).isNotNull();
		
		this.mSQLSchemaWriter = pSQLSchemaWriter;
		schemaDTOMapper = new SchemaDTOMapper(pSQLSaveStampColumnDTO);
	}
	
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		mSQLSchemaWriter.addColumn(
			TableType.BASE_CONTENT_DATA.getPrefix() + tableName,
			schemaDTOMapper.createSQLColumnDTOFrom(column)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		mSQLSchemaWriter.addTable(schemaDTOMapper.createSQLTableDTOFrom(table));
	}
	
	//method
	public void deleteColumn(final String tableName, final String columnName) {
		mSQLSchemaWriter.deleteColumn(TableType.BASE_CONTENT_DATA.getPrefix() + tableName, columnName);
	}
	
	//method
	public void deleteTable(final String tableName) {
		mSQLSchemaWriter.deleteTable(TableType.BASE_CONTENT_DATA.getPrefix() + tableName);
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
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		mSQLSchemaWriter.renameColumn(
			TableType.BASE_CONTENT_DATA.getPrefix() + tableName,
			columnName,
			newColumnName
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		mSQLSchemaWriter.renameTable(
			TableType.BASE_CONTENT_DATA.getPrefix() + tableName,
			TableType.BASE_CONTENT_DATA.getPrefix() + newTableName
		);
	}
}
