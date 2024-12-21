package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.CreationRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.DeletionRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.EditingRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.LoadingRequestable;

public interface IDatabaseObject
extends CloseStateRequestable, DeletionRequestable, EditingRequestable, LoadingRequestable, CreationRequestable {

  DatabaseObjectState getState();

  boolean isLinkedWithRealDatabase();
}
