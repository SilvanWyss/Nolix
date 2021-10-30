//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ISaveStampConfigurationDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDTO implements ITableDTO {
	
	//attributes
	private final String name;
	private final ISaveStampConfigurationDTO saveStampConfiguration;
	
	//multi-attribute
	private final IContainer<IColumnDTO> columnDTOs;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public TableDTO(
		final String name,
		final ISaveStampConfigurationDTO saveStampConfiguration,
		final IContainer<ColumnDTO> columnDTOs
	) {
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		if (saveStampConfiguration == null) {
			throw new ArgumentIsNullException(ISaveStampConfigurationDTO.class);
		}
		
		if (columnDTOs == null) {
			throw new ArgumentIsNullException("column DTOs");
		}
		
		this.name = name;
		this.saveStampConfiguration = saveStampConfiguration;
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
	
	//method
	@Override
	public ISaveStampConfigurationDTO getSaveStampConfiguration() {
		return saveStampConfiguration;
	}
}
