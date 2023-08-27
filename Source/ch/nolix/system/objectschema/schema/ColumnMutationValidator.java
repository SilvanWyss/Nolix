//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.BaseParameterizedReferenceType;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.system.objectschema.schemahelper.DatabaseHelper;
import ch.nolix.system.objectschema.schemahelper.ParametrizedPropertyTypeHelper;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IParameterizedPropertyTypeHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
final class ColumnMutationValidator {
	
	//constant
	private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();
	
	//constant
	private static final ITableHelper TABLE_HELPER = new TableHelper();
	
	//constant
	private static final IColumnHelper COLUMN_HELPER = new ColumnHelper();
	
	//constant
	private static final IParameterizedPropertyTypeHelper PARAMETRIZED_PROPERTY_TYPE_HELPER =
	new ParametrizedPropertyTypeHelper();
	
	//method
	public void assertCanDeleteColumn(final Column column) {			
		COLUMN_HELPER.assertIsOpen(column);
		COLUMN_HELPER.assertIsNotDeleted(column);
		column.assertIsNotBackReferenced();
	}
	
	//method
	public void assertCanSetNameToColumn(final Column column, final String name) {
		
		COLUMN_HELPER.assertIsOpen(column);
		
		if (column.belongsToTable()) {
			TABLE_HELPER.assertDoesNotContainColumnWithGivenName(column.getParentTable(), name);
		}
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
	}
	
	//method
	public void assertCanSetParametrizedPropertyTypeToColumn(
		final Column column,
		final IParameterizedPropertyType parameterizedPropertyType
	) {
		
		COLUMN_HELPER.assertIsOpen(column);
		column.assertIsEmpty();
		
		if (
			PARAMETRIZED_PROPERTY_TYPE_HELPER.isABaseReferenceType(parameterizedPropertyType)
			&& COLUMN_HELPER.belongsToDatabase(column)
		) {
			
			final var baseParametrizedReferenceType = (BaseParameterizedReferenceType)parameterizedPropertyType;
			final var referencedTable = baseParametrizedReferenceType.getReferencedTable();
			
			DATABASE_HELPER.assertContainsGivenTable(COLUMN_HELPER.getParentDatabase(column), referencedTable);
		}
		
		if (!PARAMETRIZED_PROPERTY_TYPE_HELPER.isABaseReferenceType(parameterizedPropertyType)) {
			column.assertIsNotBackReferenced();
		}
		
		if (
			PARAMETRIZED_PROPERTY_TYPE_HELPER.isABaseBackReferenceType(parameterizedPropertyType)
			&& COLUMN_HELPER.belongsToDatabase(column)
		) {
			
			final var baseParametrizedBackReferenceType = (BaseParameterizedBackReferenceType)parameterizedPropertyType;
			final var backReferencedColumn = baseParametrizedBackReferenceType.getBackReferencedColumn();
			
			DATABASE_HELPER.assertContainsTableWithGivenColumn(
				COLUMN_HELPER.getParentDatabase(column),
				backReferencedColumn
			);
		}
	}
	
	//method
	public void assertCanSetParentTableToColumn(final Column column, final Table parentTable) {
		
		COLUMN_HELPER.assertIsOpen(column);
		COLUMN_HELPER.assertDoesNotBelongToTable(column);
		
		TABLE_HELPER.assertDoesNotContainGivenColumn(parentTable, column);
	}
}
