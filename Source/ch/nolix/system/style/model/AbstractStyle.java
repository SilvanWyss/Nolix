package ch.nolix.system.style.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.datastructure.pair.IPair;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.style.model.IBaseStyle;
import ch.nolix.systemapi.style.model.IAttachingAttribute;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <S> is the type of a {@link AbstractStyle}.
 */
abstract class AbstractStyle<S extends IBaseStyle<S>> extends AbstractElement implements IBaseStyle<S> {
  protected static final String ATTACHING_ATTRIBUTE_HEADER = "AttachingAttribute";

  private final ImmutableList<AttachingAttribute> memberAttachingAttributes;

  private final ImmutableList<AbstractSelectingStyle> memberSubStyles;

  /**
   * Creates a new {@link AbstractStyle}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  protected AbstractStyle(
    final IContainer<? extends IAttachingAttribute> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    this.memberAttachingAttributes = //
    ImmutableList.fromIterable(attachingAttributes.to(AttachingAttribute::fromAttachingAttribute));

    this.memberSubStyles = ImmutableList.fromIterable(subStyles.to(this::createSelectingStyleFromSelectingStyle));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends IAttachingAttribute> getAttachingAttributes() {
    return memberAttachingAttributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends ISelectingStyleWithSelectors> getSubStyles() {
    return memberSubStyles;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasAttachingAttributes() {
    return memberAttachingAttributes.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAttachingAttribute(final Enum<?> tag, final String value) {
    final var attachingAttribute = AttachingAttribute.forTagAndValue(tag, value);
    final var attachingAttribtues = ImmutableList.withElements(attachingAttribute);

    return withAttachingAttributes(attachingAttribtues);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAttachingAttribute(final String attachingAttribute, final String... attachingAttributes) {
    final ILinkedList<IAttachingAttribute> allAttachingAttributes = LinkedList.createEmpty();

    allAttachingAttributes.addAtEnd(AttachingAttribute.forValue(attachingAttribute));

    for (final var a : attachingAttributes) {
      allAttachingAttributes.addAtEnd(AttachingAttribute.forValue(a));
    }

    return withAttachingAttributes(allAttachingAttributes);
  }

  @Override
  public final S withNewAttachingAttributesWhereSelectorType(
    final String selectorType,
    final String newAttachingAttribute,
    final String... newAttachingAttributes) {
    final var allNewAttachingAttribtues = //
    ContainerView.forElementAndArray(newAttachingAttribute, newAttachingAttributes);

    return withNewAttachingAttributesWhereSelectorType(selectorType, allNewAttachingAttribtues);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withReplacedAttachingAttributes(
    final IPair<String, String> attachingAttributeReplacement,
    @SuppressWarnings("unchecked") final IPair<String, String>... attachingAttributeReplacements) {
    final var allAttachingAttributeReplacements = //
    ContainerView.forElementAndArray(attachingAttributeReplacement, attachingAttributeReplacements);

    return withReplacedAttachingAttributes(allAttachingAttributeReplacements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withReplacedTaggedAttachingAttributes(
    final IPair<Enum<?>, String> attachingAttributeReplacement,
    @SuppressWarnings("unchecked") final IPair<Enum<?>, String>... attachingAttributeReplacements) {
    final var allAttachingAttributeReplacements = //
    ContainerView.forElementAndArray(attachingAttributeReplacement, attachingAttributeReplacements);

    return withReplacedTaggedAttachingAttributes(allAttachingAttributeReplacements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withSubStyle(
    final ISelectingStyleWithSelectors subStyle,
    final ISelectingStyleWithSelectors... subStyles) {
    final var allSubStyles = ContainerView.forElementAndArray(subStyle, subStyles);

    return withSubStyles(allSubStyles);
  }

  /**
   * Sets the attaching attributes of the current {@link AbstractStyle} to the
   * given element.
   * 
   * @param element
   * @throws InvalidArgumentException if an attaching attribute of the current
   *                                  {@link AbstractStyle} is not valid for the
   *                                  given element.
   */
  protected final void setAttachingAttributesToElement(IStylableElement<?> element) {
    for (final var a : getAttachingAttributes()) {
      try {
        element.addOrChangeAttribute(a.getValue());
      } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.

        final var invalidArgumentException = InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          a,
          "attaching attribute",
          "could not be added to the given " + element.getType() + " '" + element.getSpecification() + "'");

        invalidArgumentException.initCause(error);

        throw invalidArgumentException;
      }
    }
  }

  /**
   * Lets the sub styles of the current {@link AbstractStyle} style the child
   * element of the given element.
   * 
   * @param element
   */
  protected final void letSubStylesStyleChildElementsOfElement(final IStylableElement<?> element) {
    final var childElements = element.getStoredChildStylableElements();

    getSubStyles().forEach(ss -> childElements.forEach(ss::applyToElement));
  }

  /**
   * @param selectingStyle
   * @return a {@link AbstractSelectingStyle} from the given selectingStyle.
   */
  private AbstractSelectingStyle createSelectingStyleFromSelectingStyle(
    final ISelectingStyleWithSelectors selectingStyle) {
    if (selectingStyle instanceof final SelectingStyle elementSelectingStyle) {
      return elementSelectingStyle;
    }

    if (selectingStyle instanceof final DeepSelectingStyle deepSelectingStyle) {
      return deepSelectingStyle;
    }

    throw InvalidArgumentException.forArgument(selectingStyle);
  }
}
