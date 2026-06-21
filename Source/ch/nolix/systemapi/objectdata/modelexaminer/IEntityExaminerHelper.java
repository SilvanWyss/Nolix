/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelexaminer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 */
public interface IEntityExaminerHelper {
  ExtendedIterable<String> getLocallyDeletedEntitiesIds(IDatabase database);
}
