package ch.nolix.systemapi.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public interface IOptionalReferenceTool {
  Optional<IBaseBackReference> getOptionalStoredBaseBackReference(IOptionalReference<?> optionalReference);
}
