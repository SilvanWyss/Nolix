//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class TableMutationExecutor {
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//method
	public void addColumnToTable(final Table table, final Column column) {
		
		table.addColumnAttribute(column);
		column.setParentTableAttribute(table);
		
		if (table.isLinkedWithRealDatabase()) {
			table.internalgetOriRawSchemaAdapter().addColumnToTable(table, column);
		}
		
		table.internalSetEdited();
	}
	
	//method
	public void deleteTable(final Table table) {
		
		if (table.belongsToDatabase()) {
			table.getParentDatabase().removeTableAttribute(table);
		}
		
		table.internalgetOriRawSchemaAdapter().deleteTable(table);
		
		table.internalSetDeleted();
	}
	
	//method
	public void setNameToTable(final Table table, final String name) {
		
		final var oldTableName = table.getName();
		final var referencingColumns = tableHelper.getOriReferencingColumns(table);
		final var backReferencingColumns = tableHelper.getOriBackReferencingColumns(table);
		
		table.setNameAttribute(name);
		
		if (table.isLinkedWithRealDatabase()) {
			
			table.internalgetOriRawSchemaAdapter().setTableName(oldTableName, name);
			
			for (final var rc : referencingColumns) {
				((Column)rc).setParametrizedPropertyTypeToDatabase();
			}
			
			for (final var brc : backReferencingColumns) {
				((Column)brc).setParametrizedPropertyTypeToDatabase();
			}
		}
		
		table.internalSetEdited();
	}
}
