package ch.nolix.system.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.fieldtool.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public final class OptionalReferenceTool extends FieldTool implements IOptionalReferenceTool {

  @Override
  public Optional<? extends IField> getOptionalStoredBackReferencingField(
    final IOptionalReference<?> optionalReference) {
    return //
    optionalReference
      .getStoredReferencedEntity()
      .internalGetStoredFields()
      .getOptionalStoredFirst(p -> p.referencesBackField(optionalReference));
  }
}
