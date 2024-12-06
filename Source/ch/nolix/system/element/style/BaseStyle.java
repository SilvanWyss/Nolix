package ch.nolix.system.element.style;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.system.element.base.Element;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;
import ch.nolix.systemapi.elementapi.styleapi.IBaseStyle;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <S> is the type of a {@link BaseStyle}.
 */
abstract class BaseStyle<S extends IBaseStyle<S>> extends Element implements IBaseStyle<S> {

  protected static final String ATTACHING_ATTRIBUTE_HEADER = "AttachingAttribute";

  private final ImmutableList<AttachingAttribute> attachingAttributes;

  private final ImmutableList<BaseSelectingStyle> subStyles;

  /**
   * Creates a new {@link BaseStyle}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  protected BaseStyle(
    final IContainer<? extends IAttachingAttribute> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {

    this.attachingAttributes = //
    ImmutableList.forIterable(attachingAttributes.to(AttachingAttribute::fromAttachingAttribute));

    this.subStyles = ImmutableList.forIterable(subStyles.to(this::createSelectingStyleFromSelectingStyle));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends IAttachingAttribute> getAttachingAttributes() {
    return attachingAttributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends ISelectingStyleWithSelectors> getSubStyles() {
    return subStyles;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasAttachingAttributes() {
    return attachingAttributes.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAttachingAttribute(final Enum<?> tag, final String value) {

    final var attachingAttribute = AttachingAttribute.forTagAndValue(tag, value);
    final var attachingAttribtues = ImmutableList.withElement(attachingAttribute);

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
   * Sets the attaching attributes of the current {@link BaseStyle} to the given
   * element.
   * 
   * @param element
   * @throws InvalidArgumentException if an attaching attribute of the current
   *                                  {@link BaseStyle} is not valid for the given
   *                                  element.
   */
  protected final void setAttachingAttributesToElement(IStylableElement<?> element) {
    for (final var aa : getAttachingAttributes()) {
      try {
        element.addOrChangeAttribute(aa.getValue());
      } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.

        final var invalidArgumentException = InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          "attaching attribute",
          aa,
          "could not be added to the given " + element.getType() + " '" + element.getSpecification() + "'");

        invalidArgumentException.initCause(error);

        throw invalidArgumentException;
      }
    }
  }

  /**
   * Lets the sub styles of the current {@link BaseStyle} style the child element
   * of the given element.
   * 
   * @param element
   */
  protected final void letSubStylesStyleChildElementsOfElement(final IStylableElement<?> element) {

    final var childElements = element.getStoredChildStylableElements();

    getSubStyles().forEach(ss -> childElements.forEach(ss::applyToElement));
  }

  /**
   * @param selectingStyle
   * @return a {@link BaseSelectingStyle} from the given selectingStyle.
   */
  private BaseSelectingStyle createSelectingStyleFromSelectingStyle(final ISelectingStyleWithSelectors selectingStyle) {

    if (selectingStyle instanceof SelectingStyle elementSelectingStyle) {
      return elementSelectingStyle;
    }

    if (selectingStyle instanceof DeepSelectingStyle deepSelectingStyle) {
      return deepSelectingStyle;
    }

    throw InvalidArgumentException.forArgument(selectingStyle);
  }
}
