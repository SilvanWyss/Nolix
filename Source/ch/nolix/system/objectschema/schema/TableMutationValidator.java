//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedReferenceType;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class TableMutationValidator {
	
	//static attributes
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	private static final ITableHelper tableHelper = new TableHelper();
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	public void assertCanAddColumnToTable(final Table table, final Column column) {
		
		tableHelper.assertIsOpen(table);
		tableHelper.assertDoesNotContainColumnWithGivenName(table, column.getName());
		
		columnHelper.assertIsOpen(column);
		columnHelper.assertIsNew(column);
		
		if (columnHelper.isAReferenceColumn(column) && table.belongsToDatabase()) {
			
			final var baseParametrizedReferenceType = (BaseParametrizedReferenceType)column.getParametrizedPropertyType();
			final var referencedTable = baseParametrizedReferenceType.getReferencedTable();
			
			databaseHelper.assertContainsGivenTable(table.getParentDatabase(), referencedTable);
		}
		
		if (columnHelper.isABackReferenceColumn(column) && table.belongsToDatabase()) {
			
			final var baseParametrizedBackReferenceType =
			(BaseParametrizedBackReferenceType)column.getParametrizedPropertyType();
			
			final var backReferencedColumn = baseParametrizedBackReferenceType.getBackReferencedColumn();
			
			databaseHelper.assertContainsTableWithGivenColumn(table.getParentDatabase(), backReferencedColumn);
		}
	}
	
	//method
	public void assertCanDeleteTable(final Table table) {
		tableHelper.assertIsOpen(table);
		tableHelper.assertIsNotNew(table);
		tableHelper.assertIsNotDeleted(table);
		tableHelper.assertIsNotReferenced(table);
	}
	
	//method
	public void assertCanSetNameToTable(final Table table, final String name) {
		
		tableHelper.assertIsOpen(table);
		
		if (table.belongsToDatabase()) {
			databaseHelper.assertDoesNotContainTableWithGivenName(table.getParentDatabase(), name);
		}
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
}
