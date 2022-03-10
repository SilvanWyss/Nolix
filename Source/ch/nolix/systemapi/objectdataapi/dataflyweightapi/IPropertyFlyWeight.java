//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

//own imports
import ch.nolix.core.functionapi.IAction;
import ch.nolix.core.requestapi.VoidnessRequestable;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {
	
	//method declaration
	void noteUpdate();
	
	//method declaration
	void setUpdateAction(IAction updateAction);
}
