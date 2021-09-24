//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedReferenceType;

//class
final class TableMutationPreValidator {
	
	//method
	public void assertCanAddColumnToTable(final Table table, final Column column) {
		
		table.assertIsOpen();
		table.assertDoesNotContainColumnWithHeader(column.getHeader());
		
		column.assertIsOpen();
		column.assertIsNew();
		
		if (column.isIdColumn()) {
			table.assertDoesNotContainIdColumn();
		}
		
		if (column.isAnyReferenceColumn() && table.belongsToDatabase()) {
			
			final var baseParametrizedReferenceType = (BaseParametrizedReferenceType)column.getParametrizedPropertyType();
			final var referencedTable = baseParametrizedReferenceType.getReferencedTable();
				
			table.getParentDatabase().assertContainsTable(referencedTable);
		}
				
		if (column.isAnyBackReferenceColumn() && table.belongsToDatabase()) {
			
			final var baseParametrizedBackReferenceType =
			(BaseParametrizedBackReferenceType)column.getParametrizedPropertyType();
			
			final var backReferencedColumn = baseParametrizedBackReferenceType.getBackReferencedColumn();
			
			table.getParentDatabase().assertContainsTableWithColumn(backReferencedColumn);
		}
	}
	
	//method
	public void assertCanDeleteTable(final Table table) {
		table.assertIsOpen();
		table.assertIsNotNew();
		table.assertIsNotDeleted();
		table.assertIsNotReferenced();
	}
	
	//method
	public void assertCanSetNameToTable(final Table table, final String name) {
		
		table.assertIsOpen();
		
		if (table.belongsToDatabase()) {
			table.getParentDatabase().assertDoesNotContainTableWithName(name);
		}
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
}
