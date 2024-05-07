//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
 * A {@link ISelectingStyleWithSelectors} is a {@link ISelectingStyle} that can
 * have specific selectors.
 * 
 * @author Silvan Wyss
 * @date 2024-04-23
 */
public interface ISelectingStyleWithSelectors extends ISelectingStyle<ISelectingStyleWithSelectors> {

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
  IContainer<String> getSelectorRoles();

  //method declaration
  /**
   * @return the selector tokens of the current
   *         {@link ISelectingStyleWithSelectors}.
   */
  IContainer<String> getSelectorTokens();

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

  //method declaration
  /**
   * @param selectorId
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorId set.
   * @throws RuntimeException if the given selectorId is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorId(String selectorId);

  //method declaration
  /**
   * @param selectorRole
   * @param selectorRoles
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorRole and
   *         selectorRoles added.
   * @throws RuntimeException if the given selectorRole is null or blank.
   * @throws RuntimeException if the given selectorRoles is null.
   * @throws RuntimeException if one of the given selectorRoles is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorRole(String selectorRole, String... selectorRoles);

  //method declaration
  /**
   * @param selectorRoles
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorRoles
   *         added.
   * @throws RuntimeException if the given selectorRoles is null.
   * @throws RuntimeException if one of the given selectorRoles is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorRoles(IContainer<String> selectorRoles);

  //method declaration
  /**
   * @param selectorToken
   * @param selectorTokens
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorToken and
   *         selectorTokens added.
   * @throws RuntimeException if the given selectorToken is null or blank.
   * @throws RuntimeException if the given selectorTokens is null.
   * @throws RuntimeException if one of the given selectorTokens is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorToken(String selectorToken, String... selectorTokens);

  //method declaration
  /**
   * @param selectorTokens
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorTokens
   *         added.
   * @throws RuntimeException if the given selectorTokens is null.
   * @throws RuntimeException if one of the given selectorTokens is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorTokens(IContainer<String> selectorTokens);

  //method declaration
  /**
   * @param selectorType
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorType set.
   * @throws RuntimeException if the given selectorType is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorType(String selectorType);
}
