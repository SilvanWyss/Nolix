/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.model;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 */
public final class SelectingStyle extends AbstractSelectingStyle {
  public static final String TYPE_NAME = "SelectingStyle";

  public static final SelectingStyle EMPTY = new SelectingStyle();

  /**
   * Creates a new empty {@link SelectingStyle}.
   */
  private SelectingStyle() {
    super(null, null, ImmutableList.createEmpty(), ImmutableList.createEmpty(), ImmutableList.createEmpty(),
      ImmutableList.createEmpty());
  }

  /**
   * Creates a new {@link SelectingStyle}.
   * 
   * @param optionalSelectorId
   * @param optionalSelectorType
   * @param selectorRoles
   * @param selectorTokens
   * @param attachingAttributes
   * @param subStyles
   */
  private SelectingStyle(
    final String optionalSelectorId,
    final String optionalSelectorType,
    final ExtendedIterable<String> selectorRoles,
    final ExtendedIterable<String> selectorTokens,
    final ExtendedIterable<String> attachingAttributes,
    final ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles) {
    super(
      optionalSelectorId,
      optionalSelectorType,
      selectorRoles,
      selectorTokens,
      attachingAttributes,
      subStyles);
  }

  public static SelectingStyle fromSelectingStyle(
    final ISelectingStyleWithSelectors selectingStyle) {
    if (selectingStyle instanceof final SelectingStyle elementSelectingStyle) {
      return elementSelectingStyle;
    }

    String optionalSelectorId = null;
    String optionalSelectorType = null;

    return new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      selectingStyle.getSelectorRoles(),
      selectingStyle.getSelectorTokens(),
      selectingStyle.getAttachingAttributes(),
      selectingStyle.getSubStyles());
  }

  /**
   * @param specification
   * @return a new {@link SelectingStyle} from the given specification
   * @throws NullPointerException if the given specification is null
   * @throws RuntimeException     if the given specification is not valid
   */
  public static SelectingStyle fromSpecification(final Node<?> specification) {
    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final ILinkedList<String> selectorRoles = LinkedList.createEmpty();
    final ILinkedList<String> selectorTokens = LinkedList.createEmpty();
    final ILinkedList<String> attachingAttributes = LinkedList.createEmpty();
    final ILinkedList<AbstractSelectingStyle> subStyles = LinkedList.createEmpty();

    for (final var a : specification.getStoredChildNodes()) {
      switch (a.getHeader()) {
        case SELECTOR_ID_HEADER:
          optionalSelectorId = a.getSingleChildNodeHeader();
          break;
        case SELECTOR_TYPE_HEADER:
          optionalSelectorType = a.getSingleChildNodeHeader();
          break;
        case SELECTOR_ROLE_HEADER:
          selectorRoles.addAtEnd(a.getSingleChildNodeHeader());
          break;
        case SELECTOR_TOKEN_HEADER:
          selectorTokens.addAtEnd(a.getSingleChildNodeHeader());
          break;
        case ATTACHING_ATTRIBUTE_HEADER:
          attachingAttributes.addAtEnd(a.getStoredSingleChildNode().toString());
          break;
        case SelectingStyle.TYPE_NAME:
          subStyles.addAtEnd(SelectingStyle.fromSpecification(a));
          break;
        case DeepSelectingStyle.TYPE_NAME:
          subStyles.addAtEnd(DeepSelectingStyle.fromSpecification(a));
          break;
        default:
          throw //
          InvalidArgumentException.forArgumentAndArgumentName(
            specification,
            LowerCaseVariableNameCatalog.SPECIFICATION);
      }
    }

    return new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      selectorRoles,
      selectorTokens,
      attachingAttributes,
      subStyles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean selectsChildElements() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void applyToElement(final IStylableElement<?> element) {
    if (selectsElement(element)) {
      setAttachingAttributesToElement(element);
      letSubStylesStyleChildElementsOfElement(element);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withAdditionalSelectorRoles(
    final ExtendedIterable<String> additionalSelectorRoles) {
    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final ILinkedList<String> allSelectorRoles = LinkedList.createEmpty();

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    allSelectorRoles.addAtEnd(getSelectorRoles());
    allSelectorRoles.addAtEnd(additionalSelectorRoles);

    return //
    new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      allSelectorRoles,
      getSelectorTokens(),
      getAttachingAttributes(),
      getSubStyles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withAttachingAttributes(final ExtendedIterable<String> attachingAttributes) {
    String optionalSelectorId = null;
    String optionalSelectorType = null;

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    final var allAttachingAttributes = ExtendedIterableView.forIterables(getAttachingAttributes(), attachingAttributes);

    return //
    new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      getSelectorRoles(),
      getSelectorTokens(),
      allAttachingAttributes,
      getSubStyles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withSelectorId(final String selectorId) {
    Validator.assertThat(selectorId).thatIsNamed("selector id").isNotBlank();

    String optionalSelectorType = null;

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    return //
    new SelectingStyle(
      selectorId,
      optionalSelectorType,
      getSelectorRoles(),
      getSelectorTokens(),
      getAttachingAttributes(),
      getSubStyles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withAdditionalSelectorTokens(
    final ExtendedIterable<String> additionalSelectorTokens) {
    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final ILinkedList<String> allSelectorTokens = LinkedList.createEmpty();

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    allSelectorTokens.addAtEnd(getSelectorTokens());
    allSelectorTokens.addAtEnd(additionalSelectorTokens);

    return //
    new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      getSelectorRoles(),
      allSelectorTokens,
      getAttachingAttributes(),
      getSubStyles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withSelectorType(final String selectorType) {
    Validator.assertThat(selectorType).thatIsNamed("selector type").isNotBlank();

    String optionalSelectorId = null;

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    return //
    new SelectingStyle(
      optionalSelectorId,
      selectorType,
      getSelectorRoles(),
      getSelectorTokens(),
      getAttachingAttributes(),
      getSubStyles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withSubStyles(
    final ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles) {
    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final ILinkedList<ISelectingStyleWithSelectors> allSubStyles = LinkedList.createEmpty();

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    allSubStyles.addAtEnd(getSubStyles());
    allSubStyles.addAtEnd(subStyles);

    return //
    new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      getSelectorRoles(),
      getSelectorTokens(),
      getAttachingAttributes(),
      allSubStyles);
  }
}
