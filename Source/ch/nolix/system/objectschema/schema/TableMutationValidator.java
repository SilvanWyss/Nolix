//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedReferenceType;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class TableMutationValidator {
	
	//static attributes
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	private static final ITableHelper tableHelper = new TableHelper();
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	public void assertCanAddColumnToTable(final ITable table, final IColumn column) {
		
		table.assertIsOpen();
		tableHelper.assertDoesNotContainColumnWithGivenHeader(table, column.getHeader());
		
		column.assertIsOpen();
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
		table.assertIsOpen();
		table.assertIsNotNew();
		table.assertIsNotDeleted();
		tableHelper.assertIsNotReferenced(table);
	}
	
	//method
	public void assertCanSetNameToTable(final Table table, final String name) {
		
		table.assertIsOpen();
		
		if (table.belongsToDatabase()) {
			databaseHelper.assertDoesNotContainTableWithGivenName(table.getParentDatabase(), name);
		}
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
}
