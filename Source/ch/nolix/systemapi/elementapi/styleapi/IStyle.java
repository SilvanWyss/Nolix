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

  /**
   * @param subStyle
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyle and subStyles added.
   * @throws RuntimeException if the given subStyle is not valid.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  IStyle withSubStyle(ISelectingStyleWithSelectors subStyle, ISelectingStyleWithSelectors... subStyles);

  /**
   * @param subStyles
   * @return a new {@link IStyle} from the current {@link IStyle} with the given
   *         subStyles added.
   * @throws RuntimeException if the given subStyles is null.
   * @throws RuntimeException if one of the given subStyles is not valid.
   */
  IStyle withSubStyles(IContainer<? extends ISelectingStyleWithSelectors> subStyles);
}
