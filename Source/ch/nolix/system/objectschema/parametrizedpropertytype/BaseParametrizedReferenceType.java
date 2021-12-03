//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedBackReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedValueType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class BaseParametrizedReferenceType extends ParametrizedPropertyType<String>
implements IBaseParametrizedReferenceType<SchemaImplementation> {
	
	//attribute
	private final ITable<SchemaImplementation> referencedTable;
	
	//constructor
	public BaseParametrizedReferenceType(final ITable<SchemaImplementation> referencedTable) {
		
		super(String.class);
		
		Validator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();
		
		this.referencedTable = referencedTable;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<SchemaImplementation> asBaseParametrizedBackReferenceType() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<SchemaImplementation> asBaseParametrizedReferenceType() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<SchemaImplementation, ?> asBaseParametrizedValueType() {
		//TODO: Implement.
		return null;
	}
	
	//method
	public ITable<SchemaImplementation> getReferencedTable() {
		return referencedTable;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?> table) {
		return (getReferencedTable() == table);
	}
	
	//method
	@Override
	public final boolean referencesBackColumn(final IColumn<?> column) {
		return false;
	}
	
	//method
	@Override
	public final IParametrizedPropertyTypeDTO toDTO() {
		return
		new BaseParametrizedReferenceTypeDTO(
			getPropertyType(),
			getDataType().getName(),
			getReferencedTable().getName()
		);
	}
}
