//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
 * A {@link ISelectingStyle} is a {@link ISelectingStyle} that can have specific
 * selectors.
 * 
 * @author Silvan Wyss
 * @date 2024-04-23
 */
public interface ISelectingStyleWithSelectors extends ISelectingStyle {

  //method declaration
  /**
   * @return the selector id of the current {@link ISelectingStyleWithSelectors}.
   * @throws RuntimeException if the current {@link ISelectingStyleWithSelectors}
   *                          does not have a selector id.
   */
  String getSelectorId();

  //method declaration
  /**
   * @return the selector roles of the current
   *         {@link ISelectingStyleWithSelectors}.
   */
  public IContainer<String> getSelectorRoles();

  //method declaration
  /**
   * @return the selector tokens of the current
   *         {@link ISelectingStyleWithSelectors}.
   */
  public IContainer<String> getSelectorTokens();

  //method declaration
  /**
   * @return the selector type of the current
   *         {@link ISelectingStyleWithSelectors}.
   * @throws RuntimeException if the current {@link ISelectingStyleWithSelectors}
   *                          does not have a selector type.
   */
  String getSelectorType();

  //method declaration
  /**
   * @return true if the current {@link ISelectingStyleWithSelectors} has a
   *         selector id.
   */
  boolean hasSelectorId();

  //method declaration
  /**
   * @return true if the current {@link ISelectingStyleWithSelectors} has a
   *         selector type.
   */
  boolean hasSelectorType();
}
