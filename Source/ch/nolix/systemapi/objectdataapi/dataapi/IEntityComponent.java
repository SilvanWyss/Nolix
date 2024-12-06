package ch.nolix.systemapi.objectdataapi.dataapi;

public interface IEntityComponent {

  boolean belongsToEntity();

  IEntity getStoredParentEntity();
}
