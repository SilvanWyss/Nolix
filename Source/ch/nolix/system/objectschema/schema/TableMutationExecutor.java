//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class TableMutationExecutor {
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//method
	public void addColumnToTable(final Table table, final Column column) {
		
		table.addColumnAttribute(column);
		((Column)column).setParentTableAttribute(table);
		
		if (table.isLinkedWithRealDatabase()) {
			table.getRefRealSchemaAdapter().getRefRawSchemaWriter().addColumnToTable(table, column);
		}
		
		table.setEdited();
	}
	
	//method
	public void deleteTable(final Table table) {
		
		if (table.belongsToDatabase()) {
			table.getParentDatabase().removeTableAttribute(table);
		}
		
		table.getRefRealSchemaAdapter().getRefRawSchemaWriter().deleteTable(table);
		
		table.setDeleted();
	}
	
	//method
	public void setNameToTable(final Table table, final String name) {
		
		final var oldTableName = table.getName();
		final var referencingColumns = tableHelper.getRefReferencingColumns(table);
		final var backReferencingColumns = tableHelper.getRefBackReferencingColumns(table);
		
		table.setNameAttribute(name);
		
		if (table.isLinkedWithRealDatabase()) {
			
			table.getRefRealSchemaAdapter().getRefRawSchemaWriter().setTableName(oldTableName, name);
			
			for (final var rc : referencingColumns) {
				((Column)rc).setParametrizedPropertyTypeToDatabase();
			}
			
			for (final var brc : backReferencingColumns) {
				((Column)brc).setParametrizedPropertyTypeToDatabase();
			}
		}
		
		table.setEdited();
	}
}
