package ch.nolix.system.style.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.style.model.IBaseStyle;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <S> is the type of a {@link AbstractStyle}.
 */
abstract class AbstractStyle<S extends IBaseStyle<S>> extends AbstractElement implements IBaseStyle<S> {
  protected static final String ATTACHING_ATTRIBUTE_HEADER = "AttachingAttribute";

  private final ImmutableList<String> memberAttachingAttributes;

  private final ImmutableList<AbstractSelectingStyle> memberSubStyles;

  /**
   * Creates a new {@link AbstractStyle}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  protected AbstractStyle(
    final IContainer<String> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    memberAttachingAttributes = ImmutableList.fromIterable(attachingAttributes);

    memberSubStyles = ImmutableList.fromIterable(subStyles.getViewOf(this::createSelectingStyleFromSelectingStyle));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> getAttachingAttributes() {
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
  public final boolean containsAttachingAttributes() {
    return memberAttachingAttributes.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAttachingAttributes(final String... attachingAttributes) {
    final var attachingAttributesView = ContainerView.forArray(attachingAttributes);

    return withAttachingAttributes(attachingAttributesView);
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
        element.addOrChangeAttribute(a);
      } catch (final Throwable error) { //NOSONAR: All Throwable must be caught.

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
