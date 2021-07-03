//package declaration
package ch.nolix.system.databaseschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//class
public abstract class BaseParametrizedReferenceType extends ParametrizedPropertyType<Long> {
	
	//attribute
	private final ITable<?, ?, ?> referencedTable;
	
	//constructor
	public BaseParametrizedReferenceType(final ITable<?, ?, ?> referencedTable) {
		
		super(Long.class);
		
		Validator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();
		
		this.referencedTable = referencedTable;
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyControlType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return true;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
	}
	
	//method
	@Override
	public final boolean references(final ITable<?, ?, ?> table) {
		return (referencedTable == table);
	}
	
	//method
	@Override
	public final boolean referencesBack(final IColumn<?, ?> column) {
		return false;
	}
}
