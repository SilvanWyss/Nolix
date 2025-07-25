package ch.nolix.core.resourcecontrol.resourceexaminer;

import ch.nolix.coreapi.resourcecontrol.resourceclosing.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrol.resourceexaminer.IResourceExaminer;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 */
public final class ResourceExaminer implements IResourceExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isOpen(final CloseStateRequestable resource) {
    return //
    resource != null
    && resource.isOpen();
  }
}
