package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.attribute.optionalattribute.IOptionalSaveStampHolder;
import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IEntity
extends Deletable, IDatabaseObject, IIdHolder, IOptionalSaveStampHolder, ITableComponent<ITable<? extends IEntity>> {

  String getParentTableName();

  String getShortDescription();

  IDatabase getStoredParentDatabase();

  IContainer<? extends IField> internalGetStoredFields();

  void internalSetLoadedAndIdAndSaveStamp(String loadedId, String saveStamp);

  void internalSetParentTable(ITable<? extends IEntity> parentTable);

  boolean isReferencedInPersistedData();

  boolean isReferencedInPersistedDataIgnoringGivenEntities(IContainer<String> entitiesToIgnoreIds);
}
