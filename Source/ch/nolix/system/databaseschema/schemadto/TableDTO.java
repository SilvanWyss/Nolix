//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDTO implements ITableDTO {
	
	//attribute
	private final String name;
	
	//multi-attribute
	private final IContainer<IColumnDTO> columnDTOs;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public TableDTO(final String name, final IContainer<ColumnDTO> columnDTOs) {
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		if (columnDTOs == null) {
			throw new ArgumentIsNullException("column DTOs");
		}
		
		this.name = name;
		this.columnDTOs = columnDTOs.asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> getColumns() {
		return columnDTOs;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
