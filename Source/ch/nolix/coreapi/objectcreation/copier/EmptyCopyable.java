package ch.nolix.coreapi.objectcreation.copier;

/**
 * A {@link EmptyCopyable} can create an empty copy of itself.
 * 
 * @author Silvan Wyss
 * @version 2023-01-14
 * @param <C> is the type of a {@link EmptyCopyable}.
 */
public interface EmptyCopyable<C extends EmptyCopyable<C>> {
  /**
   * @return a new empty copy of the current {@link EmptyCopyable}.
   */
  C createEmptyCopy();
}
