//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDto;

//class
public abstract class BaseParametrizedReferenceType extends ParametrizedPropertyType
implements IBaseParametrizedReferenceType {
	
	//attribute
	private final ITable referencedTable;
	
	//constructor
	protected BaseParametrizedReferenceType(final ITable referencedTable) {
		
		super(DataType.STRING);
		
		GlobalValidator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();
		
		this.referencedTable = referencedTable;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType asBaseParametrizedBackReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType asBaseParametrizedReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<?> asBaseParametrizedValueType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedValueType");
	}
	
	//method
	@Override
	public ITable getReferencedTable() {
		return referencedTable;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable table) {
		return (getReferencedTable() == table);
	}
	
	//method
	@Override
	public final boolean referencesBackColumn(final IColumn column) {
		return false;
	}
	
	//method
	@Override
	public final IParametrizedPropertyTypeDto toDto() {
		return
		new BaseParametrizedReferenceTypeDto(
			getPropertyType(),
			getDataType(),
			getReferencedTable().getId()
		);
	}
}
