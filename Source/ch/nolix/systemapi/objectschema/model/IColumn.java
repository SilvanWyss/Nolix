package ch.nolix.systemapi.objectschema.model;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableNameHolder;
import ch.nolix.coreapi.attribute.mandatoryattribute.IIdHolder;
import ch.nolix.coreapi.component.datamodelcomponent.ITableComponent;
import ch.nolix.coreapi.state.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.databaseobject.model.Deletable;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

public interface IColumn
extends
Deletable,
EmptinessRequestable,
IDatabaseObject,
IFluentMutableNameHolder<IColumn>,
IIdHolder,
ITableComponent<ITable> {
  IContentModel getContentModel();

  boolean isBackReferenced();

  IColumn setContentModel(IContentModel contentModel);
}
