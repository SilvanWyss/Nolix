package ch.nolix.coreapi.resourcecontrol.savecontrol;

/**
 * A {@link ChangeRequestable} can be asked if it has uncommitted changes.
 * 
 * @author Silvan Wyss
 * @version 2021-02-26
 */
public interface ChangeRequestable {
  /**
   * @return true if the current {@link ChangeRequestable} has uncomitted changes.
   */
  boolean hasChanges();

  /**
   * @return true if the current {@link ChangeRequestable} does not have
   *         uncommitted changes.
   */
  default boolean isChangeFree() {
    return !hasChanges();
  }
}
