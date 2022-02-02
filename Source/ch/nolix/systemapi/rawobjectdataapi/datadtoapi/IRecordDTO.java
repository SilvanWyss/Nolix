//package declaration
package ch.nolix.systemapi.rawobjectdataapi.datadtoapi;

import ch.nolix.core.container.IContainer;

//interface
public interface IRecordDTO {
	
	//method declaration
	IContainer<ILoadedContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
}
