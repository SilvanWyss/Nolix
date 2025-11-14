package ch.nolix.system.style.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class DeepSelectingStyle extends AbstractSelectingStyle {
  public static final String TYPE_NAME = "DeepSelectingStyle";

  /**
   * Creates a new empty {@link DeepSelectingStyle}.
   */
  public DeepSelectingStyle() {
    super(null, null, ImmutableList.createEmpty(), ImmutableList.createEmpty(), ImmutableList.createEmpty(),
      ImmutableList.createEmpty());
  }

  /**
   * Creates a new {@link DeepSelectingStyle}.
   * 
   * @param optionalSelectorId
   * @param optionalSelectorType
   * @param selectorRoles
   * @param selectorTokens
   * @param attachingAttributes
   * @param subStyles
   */
  public DeepSelectingStyle(
    final String optionalSelectorId,
    final String optionalSelectorType,
    final IContainer<String> selectorRoles,
    final IContainer<String> selectorTokens,
    final IContainer<String> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    super(
      optionalSelectorId,
      optionalSelectorType,
      selectorRoles,
      selectorTokens,
      attachingAttributes,
      subStyles);
  }

  /**
   * @param specification
   * @return a new {@link DeepSelectingStyle} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static DeepSelectingStyle fromSpecification(final INode<?> specification) {
    String optionalselectorId = null;
    String optionalSelectorType = null;
    final ILinkedList<String> selectorRoles = LinkedList.createEmpty();
    final ILinkedList<String> selectorTokens = LinkedList.createEmpty();
    final ILinkedList<String> attachingAttributes = LinkedList.createEmpty();
    final ILinkedList<AbstractSelectingStyle> subStyles = LinkedList.createEmpty();

    for (final var a : specification.getStoredChildNodes()) {
      switch (a.getHeader()) {
        case SELECTOR_ID_HEADER:
          optionalselectorId = a.getSingleChildNodeHeader();
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
          InvalidArgumentException.forArgumentAndArgumentName(specification, LowerCaseVariableCatalog.SPECIFICATION);
      }
    }

    return new DeepSelectingStyle(
      optionalselectorId,
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
    return true;
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

    styleChildElementsOfElement(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectingStyleWithSelectors withAttachingAttributes(final IContainer<String> attachingAttributes) {
    String optionalSelectorId = null;
    String optionalSelectorType = null;

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    final var allAttachingAttributes = ContainerView.forIterables(getAttachingAttributes(), attachingAttributes);

    return //
    new DeepSelectingStyle(
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
    new DeepSelectingStyle(
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
  public ISelectingStyleWithSelectors withSelectorRoles(final IContainer<String> selectorRoles) {
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
    allSelectorRoles.addAtEnd(selectorRoles);

    return //
    new DeepSelectingStyle(
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
  public ISelectingStyleWithSelectors withSelectorTokens(final IContainer<String> selectorTokens) {
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
    allSelectorTokens.addAtEnd(selectorTokens);

    return //
    new DeepSelectingStyle(
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
    new DeepSelectingStyle(
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
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
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
    new DeepSelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      getSelectorRoles(),
      getSelectorTokens(),
      getAttachingAttributes(),
      allSubStyles);
  }

  private void styleChildElementsOfElement(final IStylableElement<?> element) {
    final var childElements = element.getStoredChildStylableElements();

    childElements.forEach(this::applyToElement);
  }
}
