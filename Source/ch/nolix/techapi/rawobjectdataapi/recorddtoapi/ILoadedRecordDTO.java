//package declaration
package ch.nolix.techapi.rawobjectdataapi.recorddtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface ILoadedRecordDTO {
	
	//method declaration
	IContainer<IContentFieldDTO> getContentFields();
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
}
