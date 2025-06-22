package ch.nolix.coreapi.creationapi.builderapi;

/**
 * A {@link IBuilder} can build {@link Object}s.
 * 
 * @author Silvan Wyss
 * @version 2023-02-13
 * @param <O> is the type of the {@link Object}s a {@link IBuilder} can build.
 */
public interface IBuilder<O> {

  /**
   * @return a new {@link Object} from the current {@link IBuilder}.
   */
  O build();
}
