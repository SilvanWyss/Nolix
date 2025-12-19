package ch.nolix.coreapi.component.datamodelcomponent;

/**
 * A {@link IEntityComponent} can belong to an entity.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the entity a {@link IEntityComponent} can belong
 *            to.
 */
public interface IEntityComponent<E> {
  /**
   * @return true if the current {@link IEntityComponent} belongs to an entity,
   *         false otherwise.
   */
  boolean belongsToEntity();

  /**
   * @return the entity of the current {@link IEntityComponent}.
   * @throws RuntimeException if the current {@link IEntityComponent} does not
   *                          belong to an entity.
   */
  E getStoredParentEntity();
}
