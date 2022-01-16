//package declaration
package ch.nolix.techapi.rawobjectschemaapi.schemadtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ITableDTO {
	
	//method declaration
	IContainer<IColumnDTO> getColumns();
	
	//method declaration
	String getId();
	
	//method declaration
	String getName();
	
	//method declaration
	ISaveStampConfigurationDTO getSaveStampConfiguration();
}
