package ch.nolix.systemapi.databaseobject.model;

import ch.nolix.coreapi.resourcecontrol.closecontroller.CloseStateRequestable;
import ch.nolix.coreapi.structure.typerequest.StateRequestable;
import ch.nolix.systemapi.databaseobject.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobject.databaseobjectrequest.CreationRequestable;
import ch.nolix.systemapi.databaseobject.databaseobjectrequest.DatabaseConnectionRequestable;
import ch.nolix.systemapi.databaseobject.databaseobjectrequest.DeletionRequestable;
import ch.nolix.systemapi.databaseobject.databaseobjectrequest.EditingRequestable;
import ch.nolix.systemapi.databaseobject.databaseobjectrequest.LoadingRequestable;

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
