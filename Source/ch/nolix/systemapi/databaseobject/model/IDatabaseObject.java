package ch.nolix.systemapi.databaseobject.model;

import ch.nolix.coreapi.resourcecontrol.closecontroller.CloseStateRequestable;
import ch.nolix.coreapi.state.staterequest.StateRequestable;
import ch.nolix.systemapi.databaseobject.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobject.request.CreationRequestable;
import ch.nolix.systemapi.databaseobject.request.DatabaseConnectionRequestable;
import ch.nolix.systemapi.databaseobject.request.DeletionRequestable;
import ch.nolix.systemapi.databaseobject.request.EditingRequestable;
import ch.nolix.systemapi.databaseobject.request.LoadingRequestable;

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
DatabaseConnectionRequestable,
DeletionRequestable,
EditingRequestable,
LoadingRequestable,
StateRequestable<DatabaseObjectState> {
}
