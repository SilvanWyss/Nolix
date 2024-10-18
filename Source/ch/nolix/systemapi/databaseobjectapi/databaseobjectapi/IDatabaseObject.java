package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

public interface IDatabaseObject
extends CloseStateRequestable, DeletionRequestable, EditedRequestable, LoadedRequestable, NewRequestable {

  DatabaseObjectState getState();

  boolean isLinkedWithRealDatabase();
}
