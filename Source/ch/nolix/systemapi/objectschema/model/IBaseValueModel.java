package ch.nolix.systemapi.objectschema.model;

public interface IBaseValueModel<V> extends IContentModel {
  Class<V> getValueClass();
}
