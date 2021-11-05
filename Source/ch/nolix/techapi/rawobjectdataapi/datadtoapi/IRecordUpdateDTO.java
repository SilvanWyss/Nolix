//package declaration
package ch.nolix.techapi.rawobjectdataapi.datadtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IRecordUpdateDTO {
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
	
	//method declaration
	IContainer<IContentFieldDTO> getUpdatedContentFields();
}
