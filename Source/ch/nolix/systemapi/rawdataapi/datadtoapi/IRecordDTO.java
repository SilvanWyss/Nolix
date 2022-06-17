//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

//own imports
import ch.nolix.core.containerapi.IContainer;

//interface
public interface IRecordDTO {
	
	//method declaration
	IContainer<IContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
}
