package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.IShortDescriptionHolder;
import ch.nolix.coreapi.attribute.optionalattribute.IOptionalSaveStampHolder;
import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.model.Deletable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IEntity
extends
Deletable,
IDatabaseObject,
IIdHolder,
IOptionalSaveStampHolder,
IShortDescriptionHolder,
ITableComponent<ITable<? extends IEntity>> {

  String getParentTableName();

  IDatabase getStoredParentDatabase();

  IContainer<? extends IField> internalGetStoredFields();

  void internalSetLoadedAndIdAndSaveStamp(String loadedId, String saveStamp);

  void internalSetParentTable(ITable<? extends IEntity> parentTable);

  boolean isReferencedInPersistedData();

  boolean isReferencedInPersistedDataIgnoringGivenEntities(IContainer<String> entitiesToIgnoreIds);
}
