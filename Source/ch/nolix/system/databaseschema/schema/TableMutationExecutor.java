//package declaration
package ch.nolix.system.databaseschema.schema;

//class
final class TableMutationExecutor {
	
	//method
	public void addColumnToTable(final Table table, final Column column) {
		
		table.addColumnAttribute(column);
		
		if (table.isLinkedWithRealDatabase()) {
			table.getRefAccessor().addColumnToCurrentTableToDatabase(column.toDTO());
		}
		
		table.setEdited();
	}
	
	//method
	public void deleteTable(final Table table) {
		
		if (table.belongsToDatabase()) {
			table.getParentDatabase().removeTableAttribute(table);
		}
		
		if (table.isLinkedWithRealDatabase()) {
			table.getRefAccessor().deleteCurrentTableFromDatabase();
		}
		
		table.setDeleted();
	}
	
	//method
	public void setNameToTable(final Table table, final String name) {
		
		final var referencingColumns = table.getRefReferencingColumns();
		final var backReferencingColumns = table.getRefBackReferencingColumns();
		
		table.setNameAttribute(name);
		
		if (table.isLinkedWithRealDatabase()) {
			
			table.getRefAccessor().setNameForCurrentTableToDatabase(name);
			
			referencingColumns.forEach(Column::setParametrizedPropertyTypeToDatabase);
			backReferencingColumns.forEach(Column::setParametrizedPropertyTypeToDatabase);
		}
		
		table.setEdited();
	}
}
