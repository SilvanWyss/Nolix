//package declaration
package ch.nolix.system.sqlbasicschema.schemadto;

import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

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
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
		
		//TODO: columns.getCopy()
		this.columns = columns;
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
