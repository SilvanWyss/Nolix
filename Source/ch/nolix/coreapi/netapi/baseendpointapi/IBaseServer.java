//package declaration
package ch.nolix.coreapi.netapi.baseendpointapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IBaseServer<
	S extends IBaseSlot<EP>,
	EP extends IBaseEndPoint
>
extends Clearable, GroupCloseable {
	
	//method declaration
	void addDefaultSlot(S defaultSlot);
	
	//method declaration
	void addSlot(S slot);
	
	//method declaration
	void removeSlot(S slot);
}
