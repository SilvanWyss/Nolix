package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IBaseValueModel<V> extends IContentModel {

  Class<V> getValueClass();
}
