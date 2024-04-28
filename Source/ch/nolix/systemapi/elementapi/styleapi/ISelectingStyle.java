//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ISelectingStyle} is a {@link IBaseStyle} that can select or skip a
 * {@link IStylableElement} that is given to be styled.
 * 
 * @author Silvan Wyss
 * @date 2023-07-08
 * @param <SS> is the type of a {@link ISelectingStyle}.
 */
@AllowDefaultMethodsAsDesignPattern
public interface ISelectingStyle<SS extends ISelectingStyle<SS>> extends IBaseStyle<SS> {

  //method declaration
  /**
   * @return true if the current {@link ISelectingStyle} would select the child
   *         elements of a given {@link IStylableElement} to style.
   */
  boolean selectsChildElements();

  //method declaration
  /**
   * @param element
   * @return true if the current {@link ISelectingStyle} would select the given
   *         element to style.
   */
  boolean selectsElement(IStylableElement<?> element);

  //method
  /**
   * @return true if the current {@link ISelectingStyle} would not (!) select the
   *         child elements of a given {@link IStylableElement} to style.
   */
  default boolean skipsChildElements() {
    return !selectsChildElements();
  }

  //method
  /**
   * @param element
   * @return true if the current {@link ISelectingStyle} would not (!) select the
   *         given element to style.
   */
  default boolean skipsElement(final IStylableElement<?> element) {
    return !selectsElement(element);
  }
}
