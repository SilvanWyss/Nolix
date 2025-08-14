package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.component.datamodelcomponent.IEntityComponent;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldrequest.MandatorynessRequestable;
import ch.nolix.coreapi.state.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public interface IField
extends EmptinessRequestable, IDatabaseObject, IEntityComponent<IEntity>, INameHolder, MandatorynessRequestable {

  IContainer<IBaseBackReference<IEntity>> getStoredAbstractBackReferencesThatReferencesBackThis();

  IContainer<IBaseReference<IEntity>> getStoredAbstractReferencesThatAreBackReferencedFromThis();

  IColumnView<ITable<IEntity>> getStoredParentColumn();

  ContentType getType();

  IContainer<IField> internalGetStoredSubFields();

  void internalSetOptionalContent(Object content);

  boolean knowsParentColumn();

  boolean referencesBackEntity(IEntity entity);

  boolean referencesBackEntityWithId(String id);

  boolean referencesBackField(IField field);

  boolean referencesEntity(IEntity entity);

  boolean referencesUninsertedEntity();

  void setUpdateAction(Runnable updateAction);
}
