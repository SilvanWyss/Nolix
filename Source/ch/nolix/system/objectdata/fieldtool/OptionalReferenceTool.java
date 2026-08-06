/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.fieldtool.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

/**
 * @author Silvan Wyss
 */
public final class OptionalReferenceTool implements IOptionalReferenceTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<BaseBackReference> getOptionalStoredBaseBackReference(
    final IOptionalReference<?> optionalReference) {
    if (optionalReference.containsAny()) {
      final var referencedEntity = optionalReference.getStoredReferencedEntity();

      final var backReference = //
      referencedEntity.internalGetStoredFields().getOptionalStoredFirst(p -> p.referencesBackField(optionalReference));

      return backReference.map(b -> (BaseBackReference) b);
    }

    return Optional.empty();
  }
}
