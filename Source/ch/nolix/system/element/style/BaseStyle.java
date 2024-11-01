package ch.nolix.system.element.style;

import ch.nolix.core.container.containerview.ContainerView;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.base.Element;
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

  private final ImmutableList<Node> attachingAttributes;

  private final ImmutableList<BaseSelectingStyle> subStyles;

  /**
   * Creates a new {@link BaseStyle}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  protected BaseStyle(
    final IContainer<? extends INode<?>> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {

    this.attachingAttributes = ImmutableList.forIterable(attachingAttributes.to(Node::fromNode));

    this.subStyles = ImmutableList.forIterable(subStyles.to(this::createSelectingStyleFromSelectingStyle));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends INode<?>> getAttachingAttributes() {
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
   * @return true if the current {@link BaseStyle} has attaching attributes.
   */
  public final boolean hasAttachingAttributes() {
    return attachingAttributes.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withAttachingAttribute(final String attachingAttribute, final String... attachingAttributes) {

    final var allAttachingAttributes = ContainerView.forElementAndArray(attachingAttribute, attachingAttributes);

    return withAttachingAttributes(allAttachingAttributes);
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
        element.addOrChangeAttribute(aa);
      } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.

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
