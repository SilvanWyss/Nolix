//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;

//class
final class ColumnMutationPreValidator {
	
	//method
	public void assertCanDeleteColumn(final Column column) {			
		column.assertIsOpen();
		column.assertIsNotDeleted();
		column.assertIsNotIdColumn();
		column.assertIsNotBackReferenced();
	}
	
	//method
	public void assertCanSetHeaderToColumn(final Column column, final String header) {
		
		column.assertIsOpen();
		
		if (column.belongsToTable()) {
			column.getParentTable().assertDoesNotContainColumnWithHeader(header);
		}
		
		Validator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
	}
	
	//method
	public void assertCanSetParametrizedPropertyTypeToColumn(
		final Column column,
		final ParametrizedPropertyType<?> parametrizedPropertyType
	) {
		
		column.assertIsOpen();
		column.assertIsEmpty();
		
		if (parametrizedPropertyType.isIdType() && column.belongsToTable()) {
			column.getParentTable().assertDoesNotContainIdColumn();
		}
		
		if (parametrizedPropertyType.isAnyReferenceType() && column.belongsToDatabase()) {
			
			final var baseParametrizedReferenceType = (BaseParametrizedReferenceType)parametrizedPropertyType;
			final var referencedTable = baseParametrizedReferenceType.getReferencedTable();
				
			column.getParentDatabase().assertContainsTable(referencedTable);
		}
		
		if (!parametrizedPropertyType.isAnyReferenceType()) {
			column.assertIsNotBackReferenced();
		}
		
		if (parametrizedPropertyType.isAnyBackReferenceType() && column.belongsToDatabase()) {
			
			final var baseParametrizedBackReferenceType = (BaseParametrizedBackReferenceType)parametrizedPropertyType;
			final var backReferencedColumn = baseParametrizedBackReferenceType.getBackReferencedColumn();
			
			column.getParentDatabase().assertContainsTableWithColumn(backReferencedColumn);
		}
	}
	
	//method
	public void assertCanSetParentTableToColumn(final Column column, final Table parentTable) {
		
		column.assertIsOpen();
		column.assertDoesNotBelongToTable();
		
		parentTable.assertDoesNotContainColumn(column);
	}
}
