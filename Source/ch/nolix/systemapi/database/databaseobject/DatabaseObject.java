/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseobject;

import ch.nolix.baseapi.generalstate.staterequest.StateRequestable;
import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;
import ch.nolix.systemapi.database.databaserequest.CreationRequestable;
import ch.nolix.systemapi.database.databaserequest.DatabaseConnectionRequestable;
import ch.nolix.systemapi.database.databaserequest.DeletionRequestable;
import ch.nolix.systemapi.database.databaserequest.EditingRequestable;
import ch.nolix.systemapi.database.databaserequest.LoadingRequestable;

/**
 * A {@link DatabaseObject} can be connected with a real database.
 * 
 * @author Silvan Wyss
 */
public interface DatabaseObject
extends
OpennessRequestable,
CreationRequestable,
DatabaseConnectionRequestable,
DeletionRequestable,
EditingRequestable,
LoadingRequestable,
StateRequestable<DatabaseObjectState> {
  // This interface is a dedicated union of other interfaces.
}
