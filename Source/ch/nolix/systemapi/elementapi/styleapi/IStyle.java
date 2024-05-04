//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-05-29
 */
public interface IStyle extends IBaseStyle<IStyle> {

  //method declaration
  /**
   * @param attachingAttributes
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         attachingAttributes and subStyles added.
   * @throws RuntimeException if the given attachingAttributes is null.
   * @throws RuntimeException if one of the given attachingAttributes is not
   *                          valid.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  IStyle withAttachingAttributesAndSubStyles(
    IContainer<String> attachingAttributes,
    IContainer<ISelectingStyleWithSelectors> subStyles);

  /**
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyles added.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  IStyle withSubStyles(IContainer<? extends ISelectingStyleWithSelectors> subStyles);
}
