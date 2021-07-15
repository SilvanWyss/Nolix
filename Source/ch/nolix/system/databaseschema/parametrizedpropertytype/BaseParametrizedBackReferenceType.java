//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
public abstract class BaseParametrizedBackReferenceType extends ParametrizedPropertyType<Long> {
	
	//attribute
	private final IColumn<?, ?> backReferencedColumn;
	
	//constructor
	public BaseParametrizedBackReferenceType(final IColumn<?, ?> backReferencedColumn) {
		
		super(Long.class);
		
		Validator.assertThat(backReferencedColumn).thatIsNamed("back-referenced column").isNotNull();
		
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
	public final boolean references(final ITable<?, ?, ?> table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IColumn<?, ?> column) {
		return (getBackReferencedColumn() == column);
	}
}
