//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

//own imports
import ch.nolix.core.containerapi.IContainer;

//interface
public interface IRecordUpdateDTO {
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
	
	//method declaration
	IContainer<IContentFieldDTO> getUpdatedContentFields();
}
