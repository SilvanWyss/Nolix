//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemalanguageapi.ISchemaQueryCreator;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemalanguageapi.ISchemaStatementCreator;

//class
public abstract class SchemaAdapter implements ISchemaAdapter {
	
	//attribute
	private final ISchemaReader schemaReader;
	
	//attribute
	private final ISchemaWriter schemaWriter;
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//constructor
	protected SchemaAdapter(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final ISchemaQueryCreator schemaQueryCreator,
		final ISchemaStatementCreator schemaStatementCreator
	) {
		
		schemaReader =
		SchemaReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaQueryCreator(
			databaseName,
			pSQLConnectionPool,
			schemaQueryCreator
		);
		
		schemaWriter =
		SchemaWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaStatementCreator(
			databaseName,
			pSQLConnectionPool,
			schemaStatementCreator
		);
		
		getOriCloseController().createCloseDependencyTo(schemaReader);
		getOriCloseController().createCloseDependencyTo(schemaWriter);
	}
	
	//method
	@Override
	public final void addColumn(final String tableName, final IColumnDTO column) {
		schemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public final void addTable(final ITableDTO table) {
		schemaWriter.addTable(table);
	}
	
	//method
	@Override
	public final boolean columnsIsEmpty(final String tableName, final String columnName) {
		return schemaReader.columnsIsEmpty(tableName, columnName);
	}
	
	//method
	@Override
	public final void deleteColumn(final String tableName, final String columnName) {
		schemaWriter.deleteColumn(tableName, columnName);
	}
	
	//method
	@Override
	public final void deleteTable(final String tableName) {
		schemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public final CloseController getOriCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return schemaWriter.getSaveCount();
	}
	
	//method
	@Override
	public final IContainer<String> getSQLStatements() {
		return schemaWriter.getSQLStatements();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return schemaWriter.hasChanges();
	}
	
	//method
	@Override
	public final IContainer<IColumnDTO> loadColumns(final String tableName) {
		return schemaReader.loadColumns(tableName);
	}
	
	//method
	@Override
	public final IContainer<IFlatTableDTO> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public final IContainer<ITableDTO> loadTables() {
		return schemaReader.loadTables();
	}
	
	//method
	@Override
	public final void noteClose() {}
	
	//method
	@Override
	public final void renameColumn(final String tableName, final String columnName, final String newColumnName) {
		schemaWriter.renameColumn(tableName, columnName, newColumnName);
	}
	
	//method
	@Override
	public final void renameTable(final String tableName, final String newTableName) {
		schemaWriter.renameTable(tableName, newTableName);
	}
	
	//method
	@Override
	public final void reset() {
		schemaWriter.reset();
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		schemaWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public final boolean tableExists(final String tableName) {
		return schemaReader.tableExists(tableName);
	}
}