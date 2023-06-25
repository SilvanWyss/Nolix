//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemadto;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto;

//class
public final class TableDto implements ITableDto {
	
	//attribute
	private final String name;
	
	//mutli-attribute
	private final IContainer<IColumnDto> columns;
	
	//constructor
	public TableDto(final String name, final IColumnDto... columns) {
		this(name, ReadContainer.forArray(columns));
	}
	
	//constructor
	public TableDto(final String name, final IContainer<IColumnDto> columns) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
		
		this.columns = LinkedList.fromIterable(columns);
	}
	
	//method
	@Override
	public IContainer<IColumnDto> getColumns() {
		return columns;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}