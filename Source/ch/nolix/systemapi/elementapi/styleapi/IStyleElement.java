package ch.nolix.systemapi.elementapi.styleapi;

/**
 * A {@link IStyleElement} can have a {@link IStyle} to apply to itself and to
 * its child elements.
 * 
 * @author Silvan Wyss
 * @version 2022-07-23
 * @param <SE> is the type of a {@link IStyleElement}.
 */
public interface IStyleElement<SE extends IStyleElement<SE>> extends IStylableElement<SE> {

  /**
   * Applies the {@link IStyle} of the current {@link IStyleElement} to the
   * current {@link IStyleElement} and its child elements if the current
   * {@link IStyleElement} has a {{@link IStyle}
   */
  void applyStyleIfHasStyle();

  /**
   * @return true if the current {@link IStyleElement} has a {@link IStyle}.
   */
  boolean hasStyle();

  /**
   * Removes the {@link IStyle} of the current {@link IStyleElement}.
   */
  void removeStyle();

  /**
   * Sets the given configuration to the current {@link IStyleElement}.
   * 
   * @param style
   * @return the current {@link IStyleElement}.
   * @throws RuntimeException if the given configuration is null.
   */
  SE setStyle(IStyle style);
}
