package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalSaveStampHolder;
import ch.nolix.coreapi.componentapi.datamodelcomponentapi.ITableComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
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
