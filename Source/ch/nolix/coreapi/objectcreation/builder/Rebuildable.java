package ch.nolix.coreapi.objectcreation.builder;

/**
 * A {@link Rebuildable} can rebuild itself.
 * 
 * @author Silvan Wyss
 * @version 2023-11-04
 */
public interface Rebuildable {
  /**
   * Rebuilds the current {@link Rebuildable}.
   */
  void rebuild();
}
