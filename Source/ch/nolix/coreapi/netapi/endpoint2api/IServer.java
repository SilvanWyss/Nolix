//package declaration
package ch.nolix.coreapi.netapi.endpoint2api;

//own imports
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IServer extends GroupCloseable {

  // method declaration
  void addDefaultSlot(ISlot defaultSlot);

  // method declaration
  void addSlot(ISlot slot);

  // method declaration
  boolean containsDefaultSlot();

  // method declaration
  boolean containsSlotWithName(String name);
}
