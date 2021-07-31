//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IConstraintDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IDataTypeDTO;

//class
public final class ColumnDTO implements IColumnDTO {
	
	//constant
	private static final IContainer<IConstraintDTO> EMPTY_CONSTRAINT_LIST = new LinkedList<>();
	
	//attributes
	private final String name;
	private final IDataTypeDTO dataType;
	
	//multi-attribute
	private final IContainer<IConstraintDTO> constraints;
	
	//constructor
	public ColumnDTO(final String name, final IDataTypeDTO dataType) {
		this(name, dataType, EMPTY_CONSTRAINT_LIST);
	}
	
	//constructor
	public ColumnDTO(final String name, final IDataTypeDTO dataType, final IContainer<IConstraintDTO> constraints) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		Validator.assertThat(dataType).thatIsNamed(LowerCaseCatalogue.DATA_TYPE).isNotNull();
		
		this.name = name;
		this.dataType = dataType;
		this.constraints = constraints.toList();
	}
	
	//method
	@Override
	public IContainer<IConstraintDTO> getConstraints() {
		return constraints;
	}
	
	//method
	@Override
	public IDataTypeDTO getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
