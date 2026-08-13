/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseobject;

import ch.nolix.systemapi.database.databaserequest.DeletionRequestable;

/**
 * @author Silvan Wyss
 */
public interface Deletable extends DeletionRequestable {
  void delete();
}
