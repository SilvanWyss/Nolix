/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.model;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.StylableElement;

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
    ExtendedIterable<String> selectorRoles,
    ExtendedIterable<String> selectorTokens,
    final ExtendedIterable<String> attachingAttributes,
    final ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles) {
    super(attachingAttributes, subStyles);

    Validator.assertThatTheStrings(selectorRoles).areNotBlank();
    Validator.assertThatTheStrings(selectorTokens).areNotBlank();

    memberSelectorId = optionalSelectorId;
    memberSelectorType = optionalSelectorType;
    memberSelectorRoles = ImmutableList.fromIterable(selectorRoles);
    memberSelectorTokens = ImmutableList.fromIterable(selectorTokens);
  }

  public final boolean containsSelectorRole(final String selectorRole) {
    return memberSelectorRoles.containsEqual(selectorRole);
  }

  public final boolean containsSelectorRoles() {
    return memberSelectorRoles.containsAny();
  }

  public final boolean containsSelectorToken(final String selectorToken) {
    return memberSelectorTokens.containsEqual(selectorToken);
  }

  public final boolean containsSelectorTokens() {
    return memberSelectorTokens.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<Node<?>> getAttributes() {
    final ILinkedList<Node<?>> attributes = LinkedList.createEmpty();

    if (hasSelectorId()) {
      attributes.addAtEnd(ImmutableNode.withHeaderAndChildNode(SELECTOR_ID_HEADER, getSelectorId()));
    }

    if (hasSelectorType()) {
      attributes.addAtEnd(ImmutableNode.withHeaderAndChildNode(SELECTOR_TYPE_HEADER, getSelectorType()));
    }

    for (final var r : getSelectorRoles()) {
      attributes.addAtEnd(ImmutableNode.withHeaderAndChildNode(SELECTOR_ROLE_HEADER, r));
    }

    for (final var a : getAttachingAttributes()) {
      attributes.addAtEnd(
        ImmutableNode.withHeaderAndChildNode(ATTACHING_ATTRIBUTE_HEADER, ImmutableNode.fromString(a)));
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
  public final ExtendedIterable<String> getSelectorRoles() {
    return memberSelectorRoles;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<String> getSelectorTokens() {
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
  public final boolean selectsElement(StylableElement<?> element) {
    return selectorIdAllowsToSelectElement(element)
    && selectorTypeAllowsToSelectElement(element)
    && selectorRolesAllowToSelectElement(element)
    && selectorTokensAllowToSelectElement(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withAdditionalSelectorRole(final Enum<?> additionalSelectorRole) {
    final var additionalSelectorRoles = ImmutableList.withElement(additionalSelectorRole.toString());

    return withAdditionalSelectorRoles(additionalSelectorRoles);
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
    ExtendedIterableView.forArray(additionalSelectorRoles).getViewOf(Object::toString);

    return withAdditionalSelectorRoles(additionalSelectorRolesStrings);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISelectingStyleWithSelectors withAdditionalSelectorRoles(final String... additionalSelectorRoles) {
    final var selectorRolesContainer = ExtendedIterableView.forArray(additionalSelectorRoles);

    return withAdditionalSelectorRoles(selectorRolesContainer);
  }

  @Override
  public final ISelectingStyleWithSelectors withAdditionalSelectorToken(final String additionalSelectorToken) {
    final var additionalSelectorTokens = ImmutableList.withElement(additionalSelectorToken);

    return withAdditionalSelectorTokens(additionalSelectorTokens);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISelectingStyleWithSelectors withAdditionalSelectorTokens(final String... additionalSelectorTokens) {
    final var additionalSelectorTokensContainer = ExtendedIterableView.forArray(additionalSelectorTokens);

    return withAdditionalSelectorTokens(additionalSelectorTokensContainer);
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

  private boolean selectorIdAllowsToSelectElement(final StylableElement<?> element) {
    return !hasSelectorId() || element.hasId(getSelectorId());
  }

  private boolean selectorRolesAllowToSelectElement(StylableElement<?> element) {
    return !containsSelectorRoles() || getSelectorRoles().containsMatching(element::hasRole);
  }

  private boolean selectorTokensAllowToSelectElement(final StylableElement<?> element) {
    return !containsSelectorTokens() || getSelectorTokens().containsAny(element.getTokens());
  }

  private boolean selectorTypeAllowsToSelectElement(final StylableElement<?> element) {
    return !hasSelectorType() || element.isOfType(getSelectorType());
  }
}
