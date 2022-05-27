//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.core.requestapi.VoidnessRequestable;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {
	
	//method declaration
	void noteUpdate();
	
	//method declaration
	void setUpdateAction(IAction updateAction);
}
