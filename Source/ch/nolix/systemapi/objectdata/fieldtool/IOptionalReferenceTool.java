/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

/**
 * @author Silvan Wyss
 */
public interface IOptionalReferenceTool {
  /**
   * @param optionalReference
   * @return a {@link Optional} with the {@link BaseBackReference} that references
   *         back the given optionalReference if there is such a
   *         {@link BaseBackReference}, an empty {@link Optional} otherwise
   */
  Optional<BaseBackReference> getOptionalStoredBaseBackReference(IOptionalReference<?> optionalReference);
}
