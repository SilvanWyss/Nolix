//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//class
final class ColumnMutationExecutor {
	
	//method
	public void deleteColumn(final Column column) {
		
		if (column.belongsToTable()) {
			column.getParentTable().removeColumnAttribute(column);
		}
		
		column.getRefRawSchemaAdapter().getRefRawSchemaWriter().deleteColumn(column);
		
		column.setDeleted();
	}
	
	//method
	public void setHeaderToColumn(final Column column, final String name) {
		
		final var oldName = column.getName();
		final var backReferencingColumns = column.getRefBackReferencingColumns();
		
		column.setNameAttribute(name);
		
		if (column.isLinkedWithRealDatabase()) {
			column.getRefRawSchemaAdapter().getRefRawSchemaWriter().setColumnName(column, oldName, name);
		}
		
		for (final var brc : backReferencingColumns) {
			((Column)brc).setParametrizedPropertyTypeToDatabase();
		}
		
		column.setEdited();
	}
	
	//method
	public void setParametrizedPropertyTypeToColumn(
		final Column column,
		final IParametrizedPropertyType<SchemaImplementation, ?> parametrizedPropertyType
	) {
		
		column.setParametrizedPropertyTypeAttribute(parametrizedPropertyType);
		
		if (column.isLinkedWithRealDatabase()) {
			column
			.getRefRawSchemaAdapter()
			.getRefRawSchemaWriter()
			.setColumnParametrizedPropertyType(column, parametrizedPropertyType);
		}
		
		column.setEdited();
	}
}
