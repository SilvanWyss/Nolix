//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedBaseParametrizedReferenceType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedTable;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class BaseParametrizedReferenceType extends ParametrizedPropertyType<String>
implements IExtendedBaseParametrizedReferenceType<ParametrizedPropertyType<String>> {
	
	//attribute
	private final IExtendedTable<?, ?, ?> referencedTable;
	
	//constructor
	public BaseParametrizedReferenceType(final IExtendedTable<?, ?, ?> referencedTable) {
		
		super(String.class);
		
		Validator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();
		
		this.referencedTable = referencedTable;
	}
	
	//method
	public IExtendedTable<?, ?, ?> getReferencedTable() {
		return referencedTable;
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return true;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?, ?, ?> table) {
		return (getReferencedTable() == table);
	}
	
	//method
	@Override
	public final boolean referencesBackColumn(final IColumn<?, ?> column) {
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
