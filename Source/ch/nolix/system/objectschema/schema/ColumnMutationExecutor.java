//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//class
final class ColumnMutationExecutor {
	
	//method
	public void deleteColumn(final Column column) {
		
		if (column.belongsToTable()) {
			column.getParentTable().removeColumnAttribute(column);
		}
		
		column.internalGetRefRawSchemaAdapter().deleteColumn(column);
		
		column.internalSetDeleted();
	}
	
	//method
	public void setHeaderToColumn(final Column column, final String name) {
		
		final var oldName = column.getName();
		final var backReferencingColumns = column.getRefBackReferencingColumns();
		
		column.setNameAttribute(name);
		
		if (column.isLinkedWithRealDatabase()) {
			column.internalGetRefRawSchemaAdapter().setColumnName(column, oldName, name);
		}
		
		for (final var brc : backReferencingColumns) {
			((Column)brc).setParametrizedPropertyTypeToDatabase();
		}
		
		column.internalSetEdited();
	}
	
	//method
	public void setParametrizedPropertyTypeToColumn(
		final Column column,
		final IParametrizedPropertyType parametrizedPropertyType
	) {
		
		column.setParametrizedPropertyTypeAttribute(parametrizedPropertyType);
		
		if (column.isLinkedWithRealDatabase()) {
			column
			.internalGetRefRawSchemaAdapter()
			.setColumnParametrizedPropertyType(column, parametrizedPropertyType);
		}
		
		column.internalSetEdited();
	}
}
