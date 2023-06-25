//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemadto;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IConstraintDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IDataTypeDto;

//class
public final class ColumnDto implements IColumnDto {
	
	//constant
	private static final IContainer<IConstraintDto> EMPTY_CONSTRAINT_LIST = new LinkedList<>();
	
	//attributes
	private final String name;
	private final IDataTypeDto dataType;
	
	//multi-attribute
	private final IContainer<IConstraintDto> constraints;
	
	//constructor
	public ColumnDto(final String name, final IDataTypeDto dataType) {
		this(name, dataType, EMPTY_CONSTRAINT_LIST);
	}
	
	//constructor
	public ColumnDto(final String name, final IDataTypeDto dataType, final IContainer<IConstraintDto> constraints) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		GlobalValidator.assertThat(dataType).thatIsNamed(LowerCaseCatalogue.DATA_TYPE).isNotNull();
		
		this.name = name;
		this.dataType = dataType;
		
		this.constraints = LinkedList.fromIterable(constraints);
	}
	
	//constructor
	public ColumnDto(final String name, final IDataTypeDto dataType, final IConstraintDto... constraints) {
		this(name, dataType, ReadContainer.forArray(constraints));
	}
	
	//method
	@Override
	public IContainer<IConstraintDto> getConstraints() {
		return constraints;
	}
	
	//method
	@Override
	public IDataTypeDto getDataType() {
		return dataType;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}