package ch.nolix.coreapi.objectstructure.hierarchy;

/**
 * A {@link RootRequestable} can be asked if it is either a root object or a
 * child object.
 * 
 * @author Silvan Wyss
 * @version 2025-08-09
 */
public interface RootRequestable {

  /**
   * @return true if the current {@link RootRequestable} is a child object, false
   *         otherwise.
   */
  default boolean isChild() {
    return !isRoot();
  }

  /**
   * @return true if the current {@link RootRequestable} is a root object, false
   *         otherwise.
   */
  boolean isRoot();
}
