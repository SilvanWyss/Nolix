package ch.nolix.systemapi.databaseobject.property;

/**
 * @author Silvan Wyss
 */
public enum DatabaseObjectState {
  //not persisted in database
  NEW,

  //loaded from database and not (!) edited
  UNEDITED,

  //loaded from database and edited
  EDITED,

  //loaded from database and deleted
  DELETED,

  //not valid anymore
  CLOSED
}
