//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalSaveStampHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity extends Deletable, IDatabaseObject, IIdHolder, IOptionalSaveStampHolder {

  //method declaration
  boolean belongsToTable();

  //method declaration
  String getParentTableName();

  //method declaration
  String getShortDescription();

  //method declaration
  IDatabase getStoredParentDatabase();

  //method declaration
  ITable<? extends IEntity> getStoredParentTable();

  //method declaration
  IContainer<? extends IField> internalGetStoredProperties();

  //method declaration
  boolean isReferencedInPersistedData();
}
