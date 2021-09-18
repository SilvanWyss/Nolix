//package declaration
package ch.nolix.techapi.sqldataapi.recorddtoapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IRecordDTO {
	
	//method declaration
	String getId();
	
	//method declaration
	String getSaveStamp();
	
	//method
	IContainer<String> getValues();
}
