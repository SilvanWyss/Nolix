//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.requestuniversalapi.VoidnessRequestable;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {
	
	//method declaration
	void noteUpdate();
	
	//method declaration
	void setUpdateAction(IAction updateAction);
}
