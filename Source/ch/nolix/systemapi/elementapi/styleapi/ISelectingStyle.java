package ch.nolix.systemapi.elementapi.styleapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link ISelectingStyle} is a {@link IBaseStyle} that can select or skip a
 * {@link IStylableElement} that is given to be styled.
 * 
 * @author Silvan Wyss
 * @version 2023-07-08
 * @param <S> is the type of a {@link ISelectingStyle}.
 */
@AllowDefaultMethodsAsDesignPattern
public interface ISelectingStyle<S extends ISelectingStyle<S>> extends IBaseStyle<S> {

  /**
   * @return true if the current {@link ISelectingStyle} would select the child
   *         elements of a given {@link IStylableElement} to style.
   */
  boolean selectsChildElements();

  /**
   * @param element
   * @return true if the current {@link ISelectingStyle} would select the given
   *         element to style.
   */
  boolean selectsElement(IStylableElement<?> element);

  /**
   * @return true if the current {@link ISelectingStyle} would not (!) select the
   *         child elements of a given {@link IStylableElement} to style.
   */
  default boolean skipsChildElements() {
    return !selectsChildElements();
  }

  /**
   * @param element
   * @return true if the current {@link ISelectingStyle} would not (!) select the
   *         given element to style.
   */
  default boolean skipsElement(final IStylableElement<?> element) {
    return !selectsElement(element);
  }
}
