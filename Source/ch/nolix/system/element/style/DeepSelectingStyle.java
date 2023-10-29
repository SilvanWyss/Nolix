//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class DeepSelectingStyle extends BaseSelectingStyle {

  //constant
  public static final String TYPE_NAME = "DeepSelectingStyle";

  //constructor
  /**
   * Creates a new {@link DeepSelectingStyle}.
   * 
   * @param selectorIdContainer
   * @param selectorTypeContainer
   * @param selectorRoles
   * @param selectorTokens
   * @param attachingAttributes
   * @param subStyles
   */
  public DeepSelectingStyle(
    final ISingleContainer<String> selectorIdContainer,
    final ISingleContainer<String> selectorTypeContainer,
    final IContainer<String> selectorRoles,
    final IContainer<String> selectorTokens,
    final IContainer<? extends INode<?>> attachingAttributes,
    final IContainer<BaseSelectingStyle> subStyles) {
    super(
      selectorIdContainer,
      selectorTypeContainer,
      selectorRoles,
      selectorTokens,
      attachingAttributes,
      subStyles);
  }

  //static method
  /**
   * @param specification
   * @return a new {@link DeepSelectingStyle} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static DeepSelectingStyle fromSpecification(final INode<?> specification) {

    var selectorIdContainer = new SingleContainer<String>();
    var selectorTypeContainer = new SingleContainer<String>();
    final var selectorRoles = new LinkedList<String>();
    final var selectorTokens = new LinkedList<String>();
    final var attachingAttributes = new LinkedList<INode<?>>();
    final var subStyles = new LinkedList<BaseSelectingStyle>();

    for (final var a : specification.getStoredChildNodes()) {
      switch (a.getHeader()) {
        case SELECTOR_ID_HEADER:
          selectorIdContainer = new SingleContainer<>(a.getSingleChildNodeHeader());
          break;
        case SELECTOR_TYPE_HEADER:
          selectorTypeContainer = new SingleContainer<>(a.getSingleChildNodeHeader());
          break;
        case SELECTOR_ROLE_HEADER:
          selectorRoles.addAtEnd(a.getSingleChildNodeHeader());
          break;
        case SELECTOR_TOKEN_HEADER:
          selectorTokens.addAtEnd(a.getSingleChildNodeHeader());
          break;
        case ATTACHING_ATTRIBUTE_HEADER:
          attachingAttributes.addAtEnd(a.getStoredSingleChildNode());
          break;
        case SelectingStyle.TYPE_NAME:
          subStyles.addAtEnd(SelectingStyle.fromSpecification(a));
          break;
        case DeepSelectingStyle.TYPE_NAME:
          subStyles.addAtEnd(DeepSelectingStyle.fromSpecification(a));
          break;
        default:
          throw InvalidArgumentException.forArgumentNameAndArgument(
            LowerCaseCatalogue.SPECIFICATION,
            specification);
      }
    }

    return new DeepSelectingStyle(
      selectorIdContainer,
      selectorTypeContainer,
      selectorRoles,
      selectorTokens,
      attachingAttributes,
      subStyles);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean selectsChildElements() {
    return true;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void styleElement(final IStylableElement<?> element) {

    if (selectsElement(element)) {
      setAttachingAttributesToElement(element);
      letSubStylesStyleChildElementsOfElement(element);
    }

    styleChildElementsOfElement(element);
  }

  //method
  private void styleChildElementsOfElement(final IStylableElement<?> element) {

    final var childElements = element.getStoredChildStylableElements();

    childElements.forEach(this::styleElement);
  }
}
