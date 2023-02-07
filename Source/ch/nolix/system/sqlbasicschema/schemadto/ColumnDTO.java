//package declaration
package ch.nolix.system.sqlbasicschema.schemadto;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IConstraintDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IDataTypeDTO;

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
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		GlobalValidator.assertThat(dataType).thatIsNamed(LowerCaseCatalogue.DATA_TYPE).isNotNull();
		
		this.name = name;
		this.dataType = dataType;
		
		this.constraints = LinkedList.fromIterable(constraints);
	}
	
	//constructor
	public ColumnDTO(final String name, final IDataTypeDTO dataType, final IConstraintDTO... constraints) {
		this(name, dataType, ReadContainer.forArray(constraints));
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
