//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

//interface
/**
 * A {@link IBaseStyle} can style {@link IStylableElement}s. A
 * {@link IBaseStyle} can distinguish if it would style also the child elements
 * of a given {@link IStylableElement}.
 * 
 * @author Silvan Wyss
 * @date 2023-07-09
 * @param <S> is the type of a {@link IBaseStyle}.
 */
public interface IBaseStyle<S extends IBaseStyle<S>> extends IElement {

  //method declaration
  /**
   * Applies the current {@link IBaseStyle} to the given element.
   * 
   * @param element
   */
  void applyToElement(IStylableElement<?> element);

  //method declaration
  /**
   * @return the attaching attributes of the current {@link IBaseStyle}.
   */
  IContainer<? extends INode<?>> getAttachingAttributes();

  //method declaration
  /**
   * @return the sub styles of the current {@link IBaseStyle}.
   */
  IContainer<? extends ISelectingStyleWithSelectors> getSubStyles();
}
