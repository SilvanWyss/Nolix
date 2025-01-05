package ch.nolix.coreapi.resourcecontrolapi.resourceexaminerapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 */
public interface IResourceExaminer {

  /**
   * @param resource
   * @return true if the given resource is open, false otherwise.
   */
  boolean isOpen(CloseStateRequestable resource);
}
