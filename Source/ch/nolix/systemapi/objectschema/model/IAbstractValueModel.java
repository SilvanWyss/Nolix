package ch.nolix.systemapi.objectschema.model;

public interface IAbstractValueModel<V> extends IContentModel {

  Class<V> getValueClass();
}
