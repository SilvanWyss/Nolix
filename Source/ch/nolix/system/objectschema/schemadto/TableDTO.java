//package declaration
package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ISaveStampConfigurationDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDTO implements ITableDTO {
	
	//attributes
	private final String id;
	private final String name;
	private final ISaveStampConfigurationDTO saveStampConfiguration;
	
	//multi-attribute
	private final IContainer<IColumnDTO> columnDTOs;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public TableDTO(
		final String id,
		final String name,
		final ISaveStampConfigurationDTO saveStampConfiguration,
		final IContainer<IColumnDTO> columnDTOs
	) {
		
		if (id == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ID);
		}
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		if (saveStampConfiguration == null) {
			throw new ArgumentIsNullException(ISaveStampConfigurationDTO.class);
		}
		
		if (columnDTOs == null) {
			throw new ArgumentIsNullException("column DTOs");
		}
		
		this.id = id;
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
	public String getId() {
		return id;
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
