//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParametrizedReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.ParametrizedPropertyTypeHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IParametrizedPropertyTypeHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class ColumnMutationValidator {
	
	//static attributes
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	private static final ITableHelper tableHelper = new TableHelper();
	private static final IColumnHelper columnHelper = new ColumnHelper();
	private static final IParametrizedPropertyTypeHelper parametrizedProeprtyTypeHelper =
	new ParametrizedPropertyTypeHelper();
	
	//method
	public void assertCanDeleteColumn(final Column column) {			
		columnHelper.assertIsOpen(column);
		columnHelper.assertIsNotDeleted(column);
		column.assertIsNotBackReferenced();
	}
	
	//method
	public void assertCanSetNameToColumn(final Column column, final String name) {
		
		columnHelper.assertIsOpen(column);
		
		if (column.belongsToTable()) {
			tableHelper.assertDoesNotContainColumnWithGivenName(column.getParentTable(), name);
		}
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
	
	//method
	public void assertCanSetParametrizedPropertyTypeToColumn(
		final Column column,
		final IParametrizedPropertyType<SchemaImplementation> parametrizedPropertyType
	) {
		
		columnHelper.assertIsOpen(column);
		column.assertIsEmpty();
		
		if (
			parametrizedProeprtyTypeHelper.isABaseReferenceType(parametrizedPropertyType)
			&& columnHelper.belongsToDatabase(column)
		) {
			
			final var baseParametrizedReferenceType = (BaseParametrizedReferenceType)parametrizedPropertyType;
			final var referencedTable = baseParametrizedReferenceType.getReferencedTable();
			
			databaseHelper.assertContainsGivenTable(columnHelper.getParentDatabase(column), referencedTable);
		}
		
		if (!parametrizedProeprtyTypeHelper.isABaseReferenceType(parametrizedPropertyType)) {
			column.assertIsNotBackReferenced();
		}
		
		if (
			parametrizedProeprtyTypeHelper.isABaseBackReferenceType(parametrizedPropertyType)
			&& columnHelper.belongsToDatabase(column)
		) {
			
			final var baseParametrizedBackReferenceType = (BaseParametrizedBackReferenceType)parametrizedPropertyType;
			final var backReferencedColumn = baseParametrizedBackReferenceType.getBackReferencedColumn();
			
			databaseHelper.assertContainsTableWithGivenColumn(
				columnHelper.getParentDatabase(column),
				backReferencedColumn
			);
		}
	}
	
	//method
	public void assertCanSetParentTableToColumn(final Column column, final Table parentTable) {
		
		columnHelper.assertIsOpen(column);
		columnHelper.assertDoesNotBelongToTable(column);
		
		tableHelper.assertDoesNotContainGivenColumn(parentTable, column);
	}
}
