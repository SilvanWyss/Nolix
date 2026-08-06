/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldtool;

import java.util.Optional;

import ch.nolix.systemapi.objectdata.model.BaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 */
public interface IReferenceTool {
  Optional<BaseBackReference> getOptionalStoredBaseBackReference(IReference<IEntity> reference);
}
