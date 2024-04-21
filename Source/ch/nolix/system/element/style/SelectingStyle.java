//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2023-07-09
 */
public final class SelectingStyle extends BaseSelectingStyle {

  //constant
  public static final String TYPE_NAME = "SelectingStyle";

  //constructor
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
  public SelectingStyle(
    final String optionalSelectorId,
    final String optionalSelectorType,
    final IContainer<String> selectorRoles,
    final IContainer<String> selectorTokens,
    final IContainer<? extends INode<?>> attachingAttributes,
    final IContainer<BaseSelectingStyle> subStyles) {
    super(
      optionalSelectorId,
      optionalSelectorType,
      selectorRoles,
      selectorTokens,
      attachingAttributes,
      subStyles);
  }

  //static method
  /**
   * @param specification
   * @return a new {@link SelectingStyle} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static SelectingStyle fromSpecification(final INode<?> specification) {

    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final var selectorRoles = new LinkedList<String>();
    final var selectorTokens = new LinkedList<String>();
    final var attachingAttributes = new LinkedList<INode<?>>();
    final var subStyles = new LinkedList<BaseSelectingStyle>();

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
            LowerCaseVariableCatalogue.SPECIFICATION,
            specification);
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean selectsChildElements() {
    return false;
  }

  //method
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
}
