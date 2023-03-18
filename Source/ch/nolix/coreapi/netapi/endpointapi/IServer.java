//package declaration
package ch.nolix.coreapi.netapi.endpointapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IServer extends Clearable, GroupCloseable {
	
	//method declaration
	void addDefaultSlot(ISlot defaultSlot);
	
	//method declaration
	void addSlot(ISlot slot);
	
	//method declaration
	boolean containsDefaultSlot();
	
	//method declaration
	boolean containsSlotWithName(String name);
	
	//method declaration
	void removeSlotByName(String name);
}