//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseParameterizedBackReferenceType<

	C extends IColumn
>
extends ParameterizedPropertyType
implements IBaseParameterizedBackReferenceType<C>{
	
	//attribute
	private final C backReferencedColumn;
	
	//constructor
	protected BaseParameterizedBackReferenceType(final C backReferencedColumn) {
		
		GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();
		
		this.backReferencedColumn = backReferencedColumn;
	}
	
	//method
	@Override
	public final IBaseParameterizedBackReferenceType<C> asBaseParametrizedBackReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParameterizedReferenceType<?> asBaseParametrizedReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParameterizedValueType<?> asBaseParametrizedValueType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedValueType");
	}
	
	//method
	@Override
	public final C getBackReferencedColumn() {
		return backReferencedColumn;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?> table) {
		return false;
	}
}
