package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import java.util.Optional;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;

public interface IOptionalReferenceTool {

  boolean canClear(IOptionalReference<?> optionalReference);

  boolean canSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  Optional<? extends IField> getOptionalStoredBackReferencingField(IOptionalReference<?> optionalReference);
}
