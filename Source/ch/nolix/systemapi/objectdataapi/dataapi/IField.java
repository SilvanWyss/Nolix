//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//interface
public interface IField extends EmptinessRequestable, IDatabaseObject, MandatorynessRequestable, INameHolder {

  //method declaration
  boolean belongsToEntity();

  //method declaration
  IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences();

  //method
  IColumn getStoredParentColumn();

  //method declaration
  IEntity getStoredParentEntity();

  //method declaration
  IContainer<IField> getStoredReferencingFields();

  //method declaration
  FieldType getType();

  //method declaration
  IContentFieldDto internalToContentField();

  //method declaration
  boolean knowsParentColumn();

  //method declaration
  boolean referencesBackEntity(IEntity entity);

  //method declaration
  boolean referencesBackField(IField field);

  //method declaration
  boolean referencesEntity(IEntity entity);

  //method declaration
  boolean referencesUninsertedEntity();

  //method declaration
  void setUpdateAction(Runnable updateAction);
}
