//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class TableMutationValidator {
	
	//constant
	private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();
	
	//constant
	private static final ITableHelper TABLE_HELPER = new TableHelper();
	
	//constant
	private static final IColumnHelper COLUMN_HELPER = new ColumnHelper();
	
	//method
	public void assertCanAddColumnToTable(final Table table, final Column column) {
		
		TABLE_HELPER.assertIsOpen(table);
		TABLE_HELPER.assertDoesNotContainColumnWithGivenName(table, column.getName());
		
		COLUMN_HELPER.assertIsOpen(column);
		COLUMN_HELPER.assertIsNew(column);
		
		if (COLUMN_HELPER.isAReferenceColumn(column) && table.belongsToDatabase()) {
			
			final var baseParametrizedReferenceType = (BaseParameterizedReferenceType)column.getParametrizedPropertyType();
			final var referencedTable = baseParametrizedReferenceType.getReferencedTable();
			
			DATABASE_HELPER.assertContainsGivenTable(table.getParentDatabase(), referencedTable);
		}
		
		if (COLUMN_HELPER.isABackReferenceColumn(column) && table.belongsToDatabase()) {
			
			final var baseParametrizedBackReferenceType =
			(BaseParameterizedBackReferenceType)column.getParametrizedPropertyType();
			
			final var backReferencedColumn = baseParametrizedBackReferenceType.getBackReferencedColumn();
			
			DATABASE_HELPER.assertContainsTableWithGivenColumn(table.getParentDatabase(), backReferencedColumn);
		}
	}
	
	//method
	public void assertCanDeleteTable(final Table table) {
		TABLE_HELPER.assertIsOpen(table);
		TABLE_HELPER.assertIsNotNew(table);
		TABLE_HELPER.assertIsNotDeleted(table);
		TABLE_HELPER.assertIsNotReferenced(table);
	}
	
	//method
	public void assertCanSetNameToTable(final Table table, final String name) {
		
		TABLE_HELPER.assertIsOpen(table);
		
		if (table.belongsToDatabase()) {
			DATABASE_HELPER.assertDoesNotContainTableWithGivenName(table.getParentDatabase(), name);
		}
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
}
