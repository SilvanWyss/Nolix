//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

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
