package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IContentModel {

  IAbstractBackReferenceModel<?> asAbstractBackReferenceModel();

  IAbstractReferenceModel<?> asAbstractReferenceModel();

  IAbstractValueModel<?> asAbstractValueModel();

  boolean referencesTable(ITable<?> table);

  ContentType getContentType();
}
