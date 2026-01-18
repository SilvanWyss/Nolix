/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * A {@link ISelectingStyleWithSelectors} is a {@link ISelectingStyle} that can
 * have specific selectors.
 * 
 * @author Silvan Wyss
 */
public interface ISelectingStyleWithSelectors extends ISelectingStyle<ISelectingStyleWithSelectors> {
  /**
   * @return the selector id of the current {@link ISelectingStyleWithSelectors}.
   * @throws RuntimeException if the current {@link ISelectingStyleWithSelectors}
   *                          does not have a selector id.
   */
  String getSelectorId();

  /**
   * @return the selector roles of the current
   *         {@link ISelectingStyleWithSelectors}.
   */
  IContainer<String> getSelectorRoles();

  /**
   * @return the selector tokens of the current
   *         {@link ISelectingStyleWithSelectors}.
   */
  IContainer<String> getSelectorTokens();

  /**
   * @return the selector type of the current
   *         {@link ISelectingStyleWithSelectors}.
   * @throws RuntimeException if the current {@link ISelectingStyleWithSelectors}
   *                          does not have a selector type.
   */
  String getSelectorType();

  /**
   * @return true if the current {@link ISelectingStyleWithSelectors} has a
   *         selector id, false otherwise.
   */
  boolean hasSelectorId();

  /**
   * @return true if the current {@link ISelectingStyleWithSelectors} has a
   *         selector type, false otherwise.
   */
  boolean hasSelectorType();

  /**
   * @param selectorId
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorId set.
   * @throws RuntimeException if the given selectorId is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorId(String selectorId);

  /**
   * @param selectorRole
   * @param selectorRoles
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorRole and
   *         selectorRoles added.
   * @throws RuntimeException if the given selectorRole is null.
   * @throws RuntimeException if the given selectorRoles is null.
   * @throws RuntimeException if one of the given selectorRoles is null.
   */
  ISelectingStyleWithSelectors withSelectorRole(Enum<?> selectorRole, Enum<?>... selectorRoles);

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

  /**
   * @param selectorRoles
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorRoles
   *         added.
   * @throws RuntimeException if the given selectorRoles is null.
   * @throws RuntimeException if one of the given selectorRoles is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorRoles(IContainer<String> selectorRoles);

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

  /**
   * @param selectorTokens
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorTokens
   *         added.
   * @throws RuntimeException if the given selectorTokens is null.
   * @throws RuntimeException if one of the given selectorTokens is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorTokens(IContainer<String> selectorTokens);

  /**
   * @param selectorType
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorType set.
   * @throws RuntimeException if the given selectorType is null.
   */
  ISelectingStyleWithSelectors withSelectorType(final Class<?> selectorType);

  /**
   * @param selectorType
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorType set.
   * @throws RuntimeException if the given selectorType is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorType(String selectorType);
}
