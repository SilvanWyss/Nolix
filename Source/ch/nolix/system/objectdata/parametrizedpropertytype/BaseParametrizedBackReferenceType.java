//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

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
	public BaseParametrizedBackReferenceType(final C backReferencedColumn) {
		
		Validator.assertThat(backReferencedColumn).thatIsNamed("back referenced column").isNotNull();
		
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
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<IMPL, ?> asBaseParametrizedValueType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedValueType");
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
