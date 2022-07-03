//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;

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
