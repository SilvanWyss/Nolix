package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.coreapi.structureapi.typerequestapi.StateRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.CreationRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.DeletionRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.EditingRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.LoadingRequestable;

/**
 * A {@link IDatabaseObject} can be connected with a real database.
 * 
 * @author Silvan Wyss
 * @version 2021-07-03
 */
public interface IDatabaseObject
extends
CloseStateRequestable,
CreationRequestable,
DeletionRequestable,
EditingRequestable,
LoadingRequestable,
StateRequestable<DatabaseObjectState> {

  boolean isConnectedWithRealDatabase();
}
