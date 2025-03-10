package ch.nolix.systemapi.objectschemaapi.modelapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.componentapi.datamodelcomponentapi.ITableComponent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IColumn
extends
Deletable,
EmptinessRequestable,
IDatabaseObject,
IFluentMutableNameHolder<IColumn>,
IIdHolder,
ITableComponent<ITable> {

  IContainer<IContentModel> getContentModels();

  boolean isBackReferenced();

  IColumn setContentModels(IContainer<IContentModel> contentModels);
}
