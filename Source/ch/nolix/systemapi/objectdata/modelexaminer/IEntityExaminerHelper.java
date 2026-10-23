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
  /**
   * @param database
   * @return the ids of the Entitys that are deleted locally in the given database
   */
  ExtendedIterable<String> getLocallyDeletedEntitiesIds(IDatabase database);
}
