/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.fieldtool.IReferenceTool;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 */
public final class ReferenceTool implements IReferenceTool {
  @Override
  public Optional<IBaseBackReference> getOptionalStoredBaseBackReference(final IReference<IEntity> reference) {
    if (reference.containsAny()) {
      final var referencedEntity = reference.getStoredReferencedEntity();

      final var backReference = //
      referencedEntity.internalGetStoredFields().getOptionalStoredFirst(p -> p.referencesBackField(reference));

      return backReference.map(b -> (IBaseBackReference) b);
    }

    return Optional.empty();
  }
}
