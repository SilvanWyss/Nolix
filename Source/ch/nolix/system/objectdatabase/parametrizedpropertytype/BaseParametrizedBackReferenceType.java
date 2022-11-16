//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseParametrizedBackReferenceType<
	IMPL,
	C extends IColumn<IMPL>
>
extends ParametrizedPropertyType<IMPL>
implements IBaseParametrizedBackReferenceType<IMPL, C>{
	
	//attribute
	private final C backReferencedColumn;
	
	//constructor
	protected BaseParametrizedBackReferenceType(final C backReferencedColumn) {
		
		GlobalValidator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();
		
		this.backReferencedColumn = backReferencedColumn;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<IMPL, C> asBaseParametrizedBackReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<IMPL, ?> asBaseParametrizedReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<IMPL, ?> asBaseParametrizedValueType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedValueType");
	}
	
	//method
	@Override
	public final C getBackReferencedColumn() {
		return backReferencedColumn;
	}
	
	//method
	@Override
	public final <E2 extends IEntity<IMPL>> boolean referencesTable(final ITable<IMPL, E2> table) {
		return false;
	}
}
