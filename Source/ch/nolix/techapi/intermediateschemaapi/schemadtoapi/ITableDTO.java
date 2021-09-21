//package declaration
package ch.nolix.techapi.intermediateschemaapi.schemadtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ITableDTO {
	
	//method declaration
	IContainer<IColumnDTO> getColumns();
	
	//method declaration
	String getName();
	
	//method declaration
	ISaveStampConfigurationDTO getSaveStampConfiguration();
}
