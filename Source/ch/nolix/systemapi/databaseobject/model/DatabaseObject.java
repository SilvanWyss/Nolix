/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.databaseobject.model;

import ch.nolix.baseapi.generalstate.staterequest.StateRequestable;
import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.databaseobject.request.CreationRequestable;
import ch.nolix.systemapi.databaseobject.request.DatabaseConnectionRequestable;
import ch.nolix.systemapi.databaseobject.request.DeletionRequestable;
import ch.nolix.systemapi.databaseobject.request.EditingRequestable;
import ch.nolix.systemapi.databaseobject.request.LoadingRequestable;

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
