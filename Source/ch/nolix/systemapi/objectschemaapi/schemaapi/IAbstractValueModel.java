package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IAbstractValueModel<V> extends IContentModel {

  Class<V> getValueClass();
}
