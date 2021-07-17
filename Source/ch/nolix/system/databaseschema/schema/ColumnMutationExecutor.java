//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;

//class
final class ColumnMutationExecutor {
	
	//method
	public void deleteColumn(final Column column) {
		
		if (column.belongsToTable()) {
			column.getParentTable().removeColumnAttribute(column);
		}
		
		column.getRefAccessor().deleteCurrentColumnFromDatabase();
		
		column.setDeleted();
	}
	
	//method
	public void setHeaderToColumn(final Column column, final String header) {
		
		final var backReferencingColumns = column.getRefBackReferencingColumns();
		
		column.setHeaderAttribute(header);
		
		if (column.isLinkedWithRealDatabase()) {
			column.getRefAccessor().setHeaderOfCurrentColumnToDatabase(header);
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
			.getRefAccessor()
			.setParametrizedPropertyTypeForCurrentColumnToDatabase(parametrizedPropertyType.toDTO());
		}
		
		column.setEdited();
	}
}
