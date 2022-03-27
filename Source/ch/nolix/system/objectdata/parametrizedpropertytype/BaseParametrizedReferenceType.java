//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public abstract class BaseParametrizedReferenceType<
	IMPL,
	E extends IEntity<IMPL>
>
extends ParametrizedPropertyType<IMPL>
implements IBaseParametrizedReferenceType<IMPL, E> {
	
	//attribute
	private final ITable<IMPL, E> referencedTable;
	
	//constructor
	public BaseParametrizedReferenceType(final ITable<IMPL, E> referencedTable) {
		
		Validator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();
		
		this.referencedTable = referencedTable;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<IMPL, ?> asBaseParametrizedBackReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<IMPL, ?> asBaseParametrizedReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<IMPL, ?> asBaseParametrizedValueType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedValueType");
	}
	
	//method
	@Override
	public final ITable<IMPL, E> getRefencedTable() {
		return referencedTable;
	}
	
	//method
	@Override
	public final <E2 extends IEntity<IMPL>> boolean referencesTable(final ITable<IMPL, E2> table) {
		return (getRefencedTable() == table);
	}
}
