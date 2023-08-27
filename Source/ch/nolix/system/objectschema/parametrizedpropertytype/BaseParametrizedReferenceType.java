//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public abstract class BaseParametrizedReferenceType extends ParametrizedPropertyType
implements IBaseParameterizedReferenceType {
	
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
	public final IBaseParameterizedBackReferenceType asBaseParametrizedBackReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParameterizedReferenceType asBaseParametrizedReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParameterizedValueType<?> asBaseParametrizedValueType() {
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
	public final IParameterizedPropertyTypeDto toDto() {
		return
		new BaseParametrizedReferenceTypeDto(
			getPropertyType(),
			getDataType(),
			getReferencedTable().getId()
		);
	}
}
