package ch.nolix.system.style.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.style.model.IAttachingAttribute;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.IStylableElement;

public abstract class AbstractSelectingStyle
extends AbstractStyle<ISelectingStyleWithSelectors>
implements ISelectingStyleWithSelectors {
  protected static final String SELECTOR_ID_HEADER = "SelectorId";

  protected static final String SELECTOR_TYPE_HEADER = "SelectorType";

  protected static final String SELECTOR_ROLE_HEADER = "SelectorRole";

  protected static final String SELECTOR_TOKEN_HEADER = "SelectorToken";

  private final String selectorId;

  private final String selectorType;

  private final ImmutableList<String> selectorRoles;

  private final ImmutableList<String> selectorTokens;

  protected AbstractSelectingStyle(
    final String optionalSelectorId,
    final String optionalSelectorType,
    IContainer<String> selectorRoles,
    IContainer<String> selectorTokens,
    final IContainer<? extends IAttachingAttribute> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    super(attachingAttributes, subStyles);

    Validator.assertThatTheStrings(selectorRoles).areNotBlank();
    Validator.assertThatTheStrings(selectorTokens).areNotBlank();

    selectorId = optionalSelectorId;
    selectorType = optionalSelectorType;
    this.selectorRoles = ImmutableList.forIterable(selectorRoles);
    this.selectorTokens = ImmutableList.forIterable(selectorTokens);
  }

  public final boolean containsSelectorRole(final String selectorRole) {
    return selectorRoles.containsEqualing(selectorRole);
  }

  public final boolean containsSelectorRoles() {
    return selectorRoles.containsAny();
  }

  public final boolean containsSelectorToken(final String selectorToken) {
    return selectorTokens.containsEqualing(selectorToken);
  }

  public final boolean containsSelectorTokens() {
    return selectorTokens.containsAny();
  }

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
      attributes.addAtEnd(a.getSpecification());
    }

    for (final var s : getSubStyles()) {
      attributes.addAtEnd(s.getSpecification());
    }

    return attributes;
  }

  @Override
  public final String getSelectorId() {
    assertHasSelectorId();

    return selectorId;
  }

  @Override
  public final IContainer<String> getSelectorRoles() {
    return selectorRoles;
  }

  @Override
  public final IContainer<String> getSelectorTokens() {
    return selectorTokens;
  }

  @Override
  public final String getSelectorType() {
    assertHasSelectorType();

    return selectorType;
  }

  @Override
  public final boolean hasSelectorId() {
    return (selectorId != null);
  }

  public final boolean hasSelectorId(final String selectorId) {
    if (!hasSelectorId()) {
      return false;
    }

    return getSelectorId().equals(selectorId);
  }

  @Override
  public final boolean hasSelectorType() {
    return (selectorType != null);
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

  @Override
  public final boolean selectsElement(IStylableElement<?> element) {
    return selectorIdAllowsToSelectElement(element)
    && selectorTypeAllowsToSelectElement(element)
    && selectorRolesAllowToSelectElement(element)
    && selectorTokensAllowToSelectElement(element);
  }

  @Override
  public final ISelectingStyleWithSelectors withSelectorRole(final Enum<?> selectorRole,
    final Enum<?>... selectorRoles) {
    final var allSelectorRoles = ContainerView.forElementAndArray(selectorRole, selectorRoles).to(Object::toString);

    return withSelectorRoles(allSelectorRoles);
  }

  @Override
  public final ISelectingStyleWithSelectors withSelectorRole(final String selectorRole, final String... selectorRoles) {
    final var allSelectorRoles = ContainerView.forElementAndArray(selectorRole, selectorRoles);

    return withSelectorRoles(allSelectorRoles);
  }

  @Override
  public final ISelectingStyleWithSelectors withSelectorToken(
    final String selectorToken,
    final String... selectorTokens) {
    final var allSelectorTokens = ContainerView.forElementAndArray(selectorToken, selectorTokens);

    return withSelectorTokens(allSelectorTokens);
  }

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
