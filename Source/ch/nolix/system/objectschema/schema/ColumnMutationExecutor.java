//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;

//class
final class ColumnMutationExecutor {
	
	//method
	public void deleteColumn(final Column column) {
		
		if (column.belongsToTable()) {
			column.getParentTable().removeColumnAttribute(column);
		}
		
		column.getRefRealSchemaAdapter().getRefIntermediateSchemaWriter().deleteColumn(column);
		
		column.setDeleted();
	}
	
	//method
	public void setHeaderToColumn(final Column column, final String header) {
		
		final var oldHeader = column.getHeader();
		final var backReferencingColumns = column.getRefBackReferencingColumns();
		
		column.setHeaderAttribute(header);
		
		if (column.isLinkedWithRealDatabase()) {
			column.getRefRealSchemaAdapter().getRefIntermediateSchemaWriter().setColumnHeader(column, oldHeader, header);
		}
		
		backReferencingColumns.forEach(Column::setParametrizedPropertyTypeToDatabase);
		
		column.setEdited();
	}
	
	//method
	public void setParametrizedPropertyTypeToColumn(
		final Column column,
		final ParametrizedPropertyType<?> parametrizedPropertyType
	) {
		
		column.setParametrizedPropertyTypeAttribute(parametrizedPropertyType);
		
		if (column.isLinkedWithRealDatabase()) {
			column
			.getRefRealSchemaAdapter()
			.getRefIntermediateSchemaWriter()
			.setColumnParametrizedPropertyType(column, parametrizedPropertyType);
		}
		
		column.setEdited();
	}
}
