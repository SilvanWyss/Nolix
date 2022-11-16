//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

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
	protected BaseParametrizedReferenceType(final ITable<IMPL, E> referencedTable) {
		
		GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();
		
		this.referencedTable = referencedTable;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<IMPL, ?> asBaseParametrizedBackReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<IMPL, ?> asBaseParametrizedReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<IMPL, ?> asBaseParametrizedValueType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedValueType");
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
