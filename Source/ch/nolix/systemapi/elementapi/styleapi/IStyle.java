//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-05-29
 */
public interface IStyle extends IBaseStyle {

  //method declaration
  /**
   * @return the sub styles of the current {@link IStyle}.
   */
  IContainer<? extends ISelectingStyle> getSubStyles();

  //method declaration
  /**
   * @param attachingAttributes
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with
   *         additionally the given attachingAttributes and selectingStyes.
   * @throws RuntimeException if the given attachingAttributes is null.
   * @throws RuntimeException if one of the given attachingAttributes is blank.
   * @throws RuntimeException if the given subStyles is null.
   */
  IStyle withAttachingAttributesAndSubStyles(
      IContainer<String> attachingAttributes,
      IContainer<ISelectingStyle> subStyles);
}
