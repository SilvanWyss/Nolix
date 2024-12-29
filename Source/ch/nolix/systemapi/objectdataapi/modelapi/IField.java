package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IField
extends EmptinessRequestable, IDatabaseObject, IEntityComponent, INameHolder, MandatorynessRequestable {

  IContainer<IAbstractBackReference<IEntity>> getStoredBaseBackReferences();

  IColumn getStoredParentColumn();

  /**
   * @return the {@link IField}s the current {@link IField} references back.
   */
  IContainer<IAbstractReference<IEntity>> getStoredBackReferencedFieldsFrom();

  ContentType getType();

  void internalSetOrClearContent(Object content);

  boolean knowsParentColumn();

  boolean referencesBackEntity(IEntity entity);

  boolean referencesBackField(IField field);

  boolean referencesEntity(IEntity entity);

  boolean referencesUninsertedEntity();

  void setUpdateAction(Runnable updateAction);
}
