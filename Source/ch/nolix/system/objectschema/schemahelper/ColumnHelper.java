//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IParametrizedPropertyTypeHelper;

//class
public final class ColumnHelper extends DatabaseObjectHelper implements IColumnHelper {
	
	//static attribute
	private static final IParametrizedPropertyTypeHelper parametrizedPropertyTypeHelper =
	new ParametrizedPropertyTypeHelper();
	
	//method
	@Override
	public void assertBelongsToTable(final IColumn column) {
		if (!column.belongsToTable()) {
			throw new ArgumentDoesNotBelongToParentException(column, ITable.class);
		}
	}
	
	//method
	@Override
	public void assertDoesNotBelongToTable(final IColumn column) {
		if (column.belongsToTable()) {
			throw new ArgumentBelongsToParentException(column, column.getParentTable());
		}
	}
	
	//method
	@Override
	public void assertIsABackReferenceColumn(final IColumn column) {
		if (!isABackReferenceColumn(column)) {
			throw new InvalidArgumentException(column, "is not a back reference column");
		}
	}
	
	//method
	@Override
	public void assertIsAReferenceColumn(final IColumn column) {
		if (!isAReferenceColumn(column)) {
			throw new InvalidArgumentException(column, "is not any reference column");
		}
	}
	
	//method
	@Override
	public boolean belongsToDatabase(final IColumn column) {
		return (column.belongsToTable() && column.getParentTable().belongsToDatabase());
	}
	
	//method
	@Override
	public BasePropertyType getBasePropertyType(IColumn column) {
		return getPropertyType(column).getBaseType();
	}
	
	//method
	@Override
	public IDatabase getParentDatabase(final IColumn column) {
		return column.getParentTable().getParentDatabase();
	}
	
	//method
	@Override
	public PropertyType getPropertyType(final IColumn column) {
		return column.getParametrizedPropertyType().getPropertyType();
	}
	
	//method
	@Override
	public boolean isABackReferenceColumn(final IColumn column) {
		return parametrizedPropertyTypeHelper.isABaseBackReferenceType(column.getParametrizedPropertyType());
	}
	
	//method
	@Override
	public boolean isAReferenceColumn(final IColumn column) {
		return parametrizedPropertyTypeHelper.isABaseReferenceType(column.getParametrizedPropertyType());
	}
	
	//method
	@Override
	public boolean isAValueColumn(final IColumn column) {
		return parametrizedPropertyTypeHelper.isABaseValueType(column.getParametrizedPropertyType());
	}
	
	//method
	@Override
	public boolean referencesBackGivenColumn(
		final IColumn column,
		final IColumn probableBackReferencedColumn
	) {
		return column.getParametrizedPropertyType().referencesBackColumn(probableBackReferencedColumn);
	}
	
	//method
	@Override
	public boolean referencesGivenTable(final IColumn column, final ITable table) {
		return column.getParametrizedPropertyType().referencesTable(table);
	}
}
