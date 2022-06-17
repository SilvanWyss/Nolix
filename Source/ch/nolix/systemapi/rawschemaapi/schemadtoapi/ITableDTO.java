//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

//own imports
import ch.nolix.core.containerapi.IContainer;

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
