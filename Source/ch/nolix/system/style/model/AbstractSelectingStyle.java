/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.model;

import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractSelectingStyle
extends AbstractStyle<ISelectingStyleWithSelectors>
implements ISelectingStyleWithSelectors {
  protected static final String SELECTOR_ID_HEADER = "SelectorId";

  protected static final String SELECTOR_TYPE_HEADER = "SelectorType";

  protected static final String SELECTOR_ROLE_HEADER = "SelectorRole";

  protected static final String SELECTOR_TOKEN_HEADER = "SelectorToken";

  private final String memberSelectorId;

  private final String memberSelectorType;

  private final ImmutableList<String> memberSelectorRoles;

  private final ImmutableList<String> memberSelectorTokens;

  protected AbstractSelectingStyle(
    final String optionalSelectorId,
    final String optionalSelectorType,
    IContainer<String> selectorRoles,
    IContainer<String> selectorTokens,
    final IContainer<String> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    super(attachingAttributes, subStyles);

    Validator.assertThatTheStrings(selectorRoles).areNotBlank();
    Validator.assertThatTheStrings(selectorTokens).areNotBlank();

    memberSelectorId = optionalSelectorId;
    memberSelectorType = optionalSelectorType;
    memberSelectorRoles = ImmutableList.fromIterable(selectorRoles);
    memberSelectorTokens = ImmutableList.fromIterable(selectorTokens);
  }

  public final boolean containsSelectorRole(final String selectorRole) {
    return memberSelectorRoles.containsEqualing(selectorRole);
  }

  public final boolean containsSelectorRoles() {
    return memberSelectorRoles.containsAny();
  }

  public final boolean containsSelectorToken(final String selectorToken) {
    return memberSelectorTokens.containsEqualing(selectorToken);
  }

  public final boolean containsSelectorTokens() {
    return memberSelectorTokens.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<INode<?>> getAttributes() {
    final ILinkedList<INode<?>> attributes = LinkedList.createEmpty();

    if (hasSelectorId()) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(SELECTOR_ID_HEADER, getSelectorId()));
    }

    if (hasSelectorType()) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(SELECTOR_TYPE_HEADER, getSelectorType()));
    }

    for (final var r : getSelectorRoles()) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(SELECTOR_ROLE_HEADER, r));
    }

    for (final var a : getAttachingAttributes()) {
      attributes.addAtEnd(Node.withHeaderAndChildNode(ATTACHING_ATTRIBUTE_HEADER, Node.fromString(a)));
    }

    for (final var s : getSubStyles()) {
      attributes.addAtEnd(s.getSpecification());
    }

    return attributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getSelectorId() {
    assertHasSelectorId();

    return memberSelectorId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> getSelectorRoles() {
    return memberSelectorRoles;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> getSelectorTokens() {
    return memberSelectorTokens;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getSelectorType() {
    assertHasSelectorType();

    return memberSelectorType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasSelectorId() {
    return (memberSelectorId != null);
  }

  public final boolean hasSelectorId(final String selectorId) {
    if (!hasSelectorId()) {
      return false;
    }

    return getSelectorId().equals(selectorId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasSelectorType() {
    return (memberSelectorType != null);
  }

  public final boolean hasSelectorType(final Class<?> selectorType) {
    return //
    selectorType != null
    && hasSelectorType(selectorType.getSimpleName());
  }

  public final boolean hasSelectorType(final String selectorType) {
    if (!hasSelectorType()) {
      return false;
    }

    return getSelectorType().equals(selectorType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean selectsElement(IStylableElement<?> element) {
    return selectorIdAllowsToSelectElement(element)
    && selectorTypeAllowsToSelectElement(element)
    && selectorRolesAllowToSelectElement(element)
    && selectorTokensAllowToSelectElement(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISelectingStyleWithSelectors withAdditionalSelectorRole(final String additionalSelectorRole) {
    final var additionalSelectorRoles = ImmutableList.withElement(additionalSelectorRole);

    return withAdditionalSelectorRoles(additionalSelectorRoles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISelectingStyleWithSelectors withAdditionalSelectorRoles(final Enum<?>... additionalSelectorRoles) {
    final var additionalSelectorRolesStrings = //
    ContainerView.forArray(additionalSelectorRoles).getViewOf(Object::toString);

    return withAdditionalSelectorRoles(additionalSelectorRolesStrings);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISelectingStyleWithSelectors withAdditionalSelectorRoles(final String... additionalSelectorRoles) {
    final var selectorRolesContainer = ContainerView.forArray(additionalSelectorRoles);

    return withAdditionalSelectorRoles(selectorRolesContainer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISelectingStyleWithSelectors withAdditionalSelectorTokens(final String... additionalSelectorTokens) {
    final var additionalSelectorTokensContainer = ContainerView.forArray(additionalSelectorTokens);

    return withSelectorTokens(additionalSelectorTokensContainer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISelectingStyleWithSelectors withSelectorType(final Class<?> selectorType) {
    return withSelectorType(selectorType.getSimpleName());
  }

  private void assertHasSelectorId() {
    if (!hasSelectorId()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "selector id");
    }
  }

  private void assertHasSelectorType() {
    if (!hasSelectorType()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "selector type");
    }
  }

  private boolean selectorIdAllowsToSelectElement(final IStylableElement<?> element) {
    return !hasSelectorId() || element.hasId(getSelectorId());
  }

  private boolean selectorRolesAllowToSelectElement(IStylableElement<?> element) {
    return !containsSelectorRoles() || getSelectorRoles().containsAny(element::hasRole);
  }

  private boolean selectorTokensAllowToSelectElement(final IStylableElement<?> element) {
    return !containsSelectorTokens() || getSelectorTokens().containsAnyOf(element.getTokens());
  }

  private boolean selectorTypeAllowsToSelectElement(final IStylableElement<?> element) {
    return !hasSelectorType() || element.isOfType(getSelectorType());
  }
}
