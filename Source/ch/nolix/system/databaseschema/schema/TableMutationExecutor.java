//package declaration
package ch.nolix.system.databaseschema.schema;

//class
final class TableMutationExecutor {
	
	//method
	public void addColumnToTable(final Table table, final Column column) {
		
		table.addColumnAttribute(column);
		
		if (table.isLinkedWithRealDatabase()) {
			table.getRefRealSchemaAdapter().addColumnToTable(table, column);
		}
		
		table.setEdited();
	}
	
	//method
	public void deleteTable(final Table table) {
		
		if (table.belongsToDatabase()) {
			table.getParentDatabase().removeTableAttribute(table);
		}
				
		table.getRefRealSchemaAdapter().deleteTable(table);
		
		table.setDeleted();
	}
	
	//method
	public void setNameToTable(final Table table, final String name) {
		
		final var oldTableName = table.getName();
		final var referencingColumns = table.getRefReferencingColumns();
		final var backReferencingColumns = table.getRefBackReferencingColumns();
		
		table.setNameAttribute(name);
		
		if (table.isLinkedWithRealDatabase()) {
			
			table.getRefRealSchemaAdapter().setTableName(oldTableName, name);
			
			referencingColumns.forEach(Column::setParametrizedPropertyTypeToDatabase);
			backReferencingColumns.forEach(Column::setParametrizedPropertyTypeToDatabase);
		}
		
		table.setEdited();
	}
}
