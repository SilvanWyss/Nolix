package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IEntityComponent {

  boolean belongsToEntity();

  IEntity getStoredParentEntity();
}
