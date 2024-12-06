package ch.nolix.coreapi.programstructureapi.copierapi;

/**
 * A {@link EmptyCopyable} can create an empty copy of itself.
 * 
 * @author Silvan Wyss
 * @version 2023-01-14
 * @param <EC> is the type of a {@link EmptyCopyable}.
 */
public interface EmptyCopyable<EC extends EmptyCopyable<EC>> {

  /**
   * @return a new empty copy of the current {@link EmptyCopyable}.
   */
  EC createEmptyCopy();
}
