package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

public interface IField extends EmptinessRequestable, IDatabaseObject, MandatorynessRequestable, INameHolder {

  boolean belongsToEntity();

  IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences();

  IColumn getStoredParentColumn();

  IEntity getStoredParentEntity();

  IContainer<IField> getStoredReferencingFields();

  ContentType getType();

  IContentFieldDto internalToContentField();

  boolean knowsParentColumn();

  boolean referencesBackEntity(IEntity entity);

  boolean referencesBackField(IField field);

  boolean referencesEntity(IEntity entity);

  boolean referencesUninsertedEntity();

  void setUpdateAction(Runnable updateAction);
}
