//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseParametrizedBackReferenceType<

	C extends IColumn
>
extends ParameterizedPropertyType
implements IBaseParametrizedBackReferenceType<C>{
	
	//attribute
	private final C backReferencedColumn;
	
	//constructor
	protected BaseParametrizedBackReferenceType(final C backReferencedColumn) {
		
		GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();
		
		this.backReferencedColumn = backReferencedColumn;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<C> asBaseParametrizedBackReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<?> asBaseParametrizedValueType() {
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
