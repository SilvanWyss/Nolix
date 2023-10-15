//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.elementapi.styleapi.IBaseStyle;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyle;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
abstract class BaseStyle extends Element implements IBaseStyle {

  // constant
  protected static final String ATTACHING_ATTRIBUTE_HEADER = "AttachingAttribute";

  // multi-attribute
  private final ImmutableList<Node> attachingAttributes;

  // multi-attribute
  private final ImmutableList<BaseSelectingStyle> subStyles;

  // constructor
  /**
   * Creates a new {@link BaseStyle}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  protected BaseStyle(
      final IContainer<? extends INode<?>> attachingAttributes,
      final IContainer<BaseSelectingStyle> subStyles) {

    this.attachingAttributes = ImmutableList.forIterable(attachingAttributes.to(Node::fromNode));

    this.subStyles = ImmutableList.forIterable(subStyles);
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends INode<?>> getAttachingAttributes() {
    return attachingAttributes;
  }

  // method
  /**
   * @return the sub styles of the current {@link BaseStyle}.
   */
  public final IContainer<? extends ISelectingStyle> getSubStyles() {
    return subStyles;
  }

  // method
  /**
   * @return true if the current {@link BaseStyle} has attaching attributes.
   */
  public final boolean hasAttachingAttributes() {
    return attachingAttributes.containsAny();
  }

  // method
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
      } catch (final Throwable error) { // NOSONAR: All Throwables must be caught here.

        final var invalidArgumentException = InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
            "attaching attribute",
            aa,
            "could not be added to the given " + element.getType() + " '" + element.getSpecification() + "'");

        invalidArgumentException.initCause(error);

        throw invalidArgumentException;
      }
    }
  }

  // method
  /**
   * Lets the sub styles of the current {@link BaseStyle} style the child element
   * of the given element.
   * 
   * @param element
   */
  protected final void letSubStylesStyleChildElementsOfElement(final IStylableElement<?> element) {

    final var childElements = element.getStoredChildStylableElements();

    getSubStyles().forEach(ss -> childElements.forEach(ss::styleElement));
  }
}
