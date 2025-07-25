package ch.nolix.coreapi.resourcecontrol.resourceexaminer;

import ch.nolix.coreapi.resourcecontrol.resourceclosing.CloseStateRequestable;

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
