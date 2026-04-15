/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.model;

import ch.nolix.baseapi.container.base.IContainer;

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
   * @param additionalSelectorRole
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorRole .
   * @throws RuntimeException if the given additionalSelectorRole is null or
   *                          blank.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorRole(Enum<?> additionalSelectorRole);

  /**
   * @param additionalSelectorRole
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorRole .
   * @throws RuntimeException if the given additionalSelectorRole is null or
   *                          blank.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorRole(String additionalSelectorRole);

  /**
   * @param additionalSelectorRoles
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorRoles.
   * @throws RuntimeException if the given additionalSelectorRoles is null.
   * @throws RuntimeException if one of the given additionalSelectorRoles is null.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorRoles(Enum<?>... additionalSelectorRoles);

  /**
   * @param additionalSelectorRoles
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorRoles.
   * @throws RuntimeException if the given additionalSelectorRoles is null.
   * @throws RuntimeException if one of the given additionalSelectorRoles is null
   *                          or blank.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorRoles(IContainer<String> additionalSelectorRoles);

  /**
   * @param additionalSelectorRoles
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorRoles .
   * @throws RuntimeException if the given additionalSelectorRoles is null.
   * @throws RuntimeException if one of the given additionalSelectorRoles is null
   *                          or blank.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorRoles(String... additionalSelectorRoles);

  /**
   * @param additionalSelectorToken
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorToken.
   * @throws RuntimeException if the given additionalSelectorToken is null or
   *                          blank.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorToken(String additionalSelectorToken);

  /**
   * @param additionalSelectorTokens
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorTokens.
   * @throws RuntimeException if the given additionalSelectorTokens is null.
   * @throws RuntimeException if one of the given additionalSelectorTokens is null
   *                          or blank.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorTokens(String... additionalSelectorTokens);

  /**
   * @param additionalSelectorTokens
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given
   *         additionalSelectorTokens.
   * @throws RuntimeException if the given additionalSelectorTokens is null.
   * @throws RuntimeException if one of the given additionalSelectorTokens is null
   *                          or blank.
   */
  ISelectingStyleWithSelectors withAdditionalSelectorTokens(IContainer<String> additionalSelectorTokens);

  /**
   * @param selectorId
   * @return a new {@link ISelectingStyleWithSelectors} from the current
   *         {@link ISelectingStyleWithSelectors} with the given selectorId set.
   * @throws RuntimeException if the given selectorId is null or blank.
   */
  ISelectingStyleWithSelectors withSelectorId(String selectorId);

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
