//package declaration
package ch.nolix.coreapi.resourcecontrolapi.resourcepoolapi;

//own imports
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;

//interface
public interface IResourcePool<R extends GroupCloseable> extends GroupCloseable {

  //method declaration
  R borrowResource();
}
