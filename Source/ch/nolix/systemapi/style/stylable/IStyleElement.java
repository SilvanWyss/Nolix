package ch.nolix.systemapi.style.stylable;

import ch.nolix.systemapi.style.model.IStyle;

/**
 * A {@link IStyleElement} can have a {@link IStyle} to apply to itself and to
 * its child elements.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of a {@link IStyleElement}.
 */
public interface IStyleElement<E extends IStyleElement<E>> extends IStylableElement<E> {
  /**
   * Applies the {@link IStyle} of the current {@link IStyleElement} to the
   * current {@link IStyleElement} and its child elements if the current
   * {@link IStyleElement} has a {{@link IStyle}
   */
  void applyStyleIfHasStyle();

  /**
   * @return true if the current {@link IStyleElement} has a {@link IStyle}, false
   *         otherwise.
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
  E setStyle(IStyle style);
}
