//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;

//class
final class ColumnMutationExecutor {
	
	//method
	public void deleteColumn(final Column column) {
		//TODO: Implement.
	}
	
	//method
	public void setHeaderToColumn(final Column column, final String header) {
		
		column.noteEdit();
		
		final var backRefernceColumns = column.getRefBackReferencingColumns();
		
		column.setHeaderAttribute(header);
		
		if (column.isLinkedWithActualDatabase()) {
			column.getRefAccessor().setHeaderOfCurrentColumnToDatabase(header);
		}
		
		for (final var brc : backRefernceColumns) {
			setParametrizedPropertyTypeToColumn(brc, brc.getParametrizedPropertyType());
		}
	}
	
	//method
	public void setParametrizedPropertyTypeToColumn(
		final Column column,
		final ParametrizedPropertyType<?> parametrizedPropertyType
	) {
		
		column.noteEdit();
		
		column.setParametrizedPropertyTypeAttribute(parametrizedPropertyType);
		
		if (column.isLinkedWithActualDatabase()) {
			column
			.getRefAccessor()
			.setParametrizedPropertyTypeForCurrentColumnToDatabase(parametrizedPropertyType.toDTO());
		}
	}
}
