//package declaration
package ch.nolix.coreapi.resourcecontrolapi.resourcepoolapi;

//own imports
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;

//interface
public interface IResourcePool<R extends AutoCloseable> extends GroupCloseable {

  //method declaration
  R borrowResource();
}
