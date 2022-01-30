//package declaration
package ch.nolix.systemapi.rawobjectdataapi.datadtoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface ILoadedRecordDTO {
	
	//method declaration
	IContainer<IContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
}
