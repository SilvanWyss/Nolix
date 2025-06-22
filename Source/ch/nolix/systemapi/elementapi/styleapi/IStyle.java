package ch.nolix.systemapi.elementapi.styleapi;

/**
 * @author Silvan Wyss
 * @version 2022-05-29
 */
public interface IStyle extends IAbstractStyle<IStyle> {

  /**
   * @param style
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         style added.
   * @throws RuntimeException if the given style is null.
   */
  IStyle withStyle(IStyle style);
}
