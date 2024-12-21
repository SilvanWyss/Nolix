package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IContentModel {

  IAbstractBackReferenceModel<?> asBaseParameterizedBackReferenceType();

  IAbstractReferenceModel<?> asBaseParameterizedReferenceType();

  IAbstractValueModel<?> asBaseParameterizedValueType();

  boolean referencesTable(ITable<?> table);

  ContentType getContentType();
}
