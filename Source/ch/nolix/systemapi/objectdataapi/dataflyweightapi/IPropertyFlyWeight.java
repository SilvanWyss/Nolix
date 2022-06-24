//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

import ch.nolix.core.requestuniversalapi.VoidnessRequestable;
import ch.nolix.coreapi.functionuniversalapi.IAction;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {
	
	//method declaration
	void noteUpdate();
	
	//method declaration
	void setUpdateAction(IAction updateAction);
}
