//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ShortDescripted;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity extends Deletable, IDatabaseObject, Identified, ShortDescripted {

  // method declaration
  boolean belongsToTable();

  // method declaration
  String getParentTableName();

  // method declaration
  IDatabase getStoredParentDatabase();

  // method declaration
  ITable<IEntity> getStoredParentTable();

  // method declaration
  String getSaveStamp();

  // method declaration
  boolean hasSaveStamp();

  // method declaration
  boolean isReferencedInPersistedData();

  // method declaration
  IContainer<? extends IProperty> technicalGetRefProperties();
}
