//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

import ch.nolix.coreapi.containerapi.IContainer;

//interface
public interface IRecordDTO {
	
	//method declaration
	IContainer<IContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
}
