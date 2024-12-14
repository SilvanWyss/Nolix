package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

public interface IColumn
extends Deletable, EmptinessRequestable, IDatabaseObject, IFluentMutableNameHolder<IColumn>, IIdHolder {

  boolean belongsToTable();

  IParameterizedFieldType getParameterizedFieldType();

  ITable getStoredParentTable();

  IColumn setParameterizedFieldType(IParameterizedFieldType parameterizedFieldType);

  IColumnDto toDto();
}
