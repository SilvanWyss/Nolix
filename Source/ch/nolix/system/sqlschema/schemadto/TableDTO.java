//package declaration
package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDTO implements ITableDTO {
	
	//attribute
	private final String name;
	
	//mutli-attribute
	private final IContainer<IColumnDTO> columns;
	
	//constructor
	public TableDTO(final String name, final IColumnDTO... columns) {
		this(name, ReadContainer.forArray(columns));
	}
	
	//constructor
	public TableDTO(final String name, final IContainer<IColumnDTO> columns) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
		this.columns = columns.toList();
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> getColumns() {
		return columns;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
