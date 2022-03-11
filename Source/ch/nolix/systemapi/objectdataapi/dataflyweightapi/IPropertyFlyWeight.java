//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

//own imports
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.core.requestapi.VoidnessRequestable;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {
	
	//method declaration
	void noteUpdate(IProperty<?> property);
	
	//method declaration
	void setUpdateAction(IElementTaker<IProperty<?>> updateAction);
}
