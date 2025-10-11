package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.fieldtool.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public final class OptionalReferenceTool implements IOptionalReferenceTool {
  @Override
  public Optional<IBaseBackReference> getOptionalStoredBaseBackReference(
    final IOptionalReference<?> optionalReference) {
    if (optionalReference.containsAny()) {
      final var referencedEntity = optionalReference.getStoredReferencedEntity();

      final var backReference = //
      referencedEntity.internalGetStoredFields().getOptionalStoredFirst(p -> p.referencesBackField(optionalReference));

      return backReference.map(b -> (IBaseBackReference) b);
    }

    return Optional.empty();
  }
}
