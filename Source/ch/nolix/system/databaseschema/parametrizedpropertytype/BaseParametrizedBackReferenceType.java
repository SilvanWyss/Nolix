//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.BasePropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
public abstract class BaseParametrizedBackReferenceType extends ParametrizedPropertyType<Long> {
	
	//attribute
	private final IColumn<?, ?> backReferencedColumn;
	
	//constructor
	public BaseParametrizedBackReferenceType(final IColumn<?, ?> backReferencedColumn) {
		
		super(Long.class);
		
		assertIsAnyReferenceColumn(backReferencedColumn);
		
		this.backReferencedColumn = backReferencedColumn;
	}
	
	//method
	public IColumn<?, ?> getBackReferencedColumn() {
		return backReferencedColumn;
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return true;
	}
	
	//method
	@Override
	public final boolean isAnyControlType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?, ?, ?> table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBackColumn(final IColumn<?, ?> column) {
		return (getBackReferencedColumn() == column);
	}
	
	//method
	private void assertIsAnyReferenceColumn(IColumn<?, ?> backReferencedColumn) {
		if (!isAnyReferenceColumn(backReferencedColumn)) {
			throw new InvalidArgumentException("back referenced column", backReferencedColumn, "is not any refence column");
		}
	}
	
	//method
	private boolean isAnyReferenceColumn(IColumn<?, ?> backReferencedColumn) {
		return
		backReferencedColumn.getParametrizedPropertyType().getPropertyType().getBaseType() ==
		BasePropertyType.BASE_REFERENCE;
	}
}
