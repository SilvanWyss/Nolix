package ch.nolix.systemapi.objectdata.schemaview;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public interface IContentModelView<T> {
  IBaseBackReferenceModelView<?, T> asBaseBackReferenceModel();

  IBaseReferenceModelView<T> asBaseReferenceModel();

  IBaseValueModelView<?, T> asBaseValueModel();

  FieldType getFieldType();

  boolean referencesTable(T table);
}
