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

	E extends IEntity
>
extends ParameterizedPropertyType
implements IBaseParametrizedReferenceType<E> {
	
	//attribute
	private final ITable<E> referencedTable;
	
	//constructor
	protected BaseParametrizedReferenceType(final ITable<E> referencedTable) {
		
		GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();
		
		this.referencedTable = referencedTable;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<?> asBaseParametrizedBackReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<?> asBaseParametrizedValueType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedValueType");
	}
	
	//method
	@Override
	public final ITable<E> getStoredencedTable() {
		return referencedTable;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?> table) {
		return (getStoredencedTable() == table);
	}
}
