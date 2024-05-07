//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
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
   * Creates a new empty {@link SelectingStyle}.
   */
  public SelectingStyle() {
    super(null, null, new ImmutableList<>(), new ImmutableList<>(), new ImmutableList<>(), new ImmutableList<>());
  }

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
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
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

    if (selectingStyle instanceof SelectingStyle elementSelectingStyle) {
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

  //static method
  /**
   * @param specification
   * @return a new {@link SelectingStyle} from the given specification.
   * @throws NullPointerException     if the given specification is null.
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SelectingStyle withAttachingAttributes(final IContainer<String> attachingAttributes) {

    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final var allAttachingAttributes = new LinkedList<Node>();

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    for (final var aa : getAttachingAttributes()) {
      allAttachingAttributes.addAtEnd(Node.fromNode(aa));
    }

    for (final var aa : attachingAttributes) {
      allAttachingAttributes.addAtEnd(Node.fromString(aa));
    }

    return //
    new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      getSelectorRoles(),
      getSelectorTokens(),
      allAttachingAttributes,
      getSubStyles());
  }

  //method
  @Override
  public ISelectingStyleWithSelectors withSelectorId(final String selectorId) {

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

  //method
  @Override
  public ISelectingStyleWithSelectors withSelectorRoles(final IContainer<String> selectorRoles) {

    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final var allSelectorRoles = new LinkedList<String>();

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    allSelectorRoles.addAtEnd(getSelectorRoles());
    allSelectorRoles.addAtEnd(selectorRoles);

    return //
    new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      allSelectorRoles,
      getSelectorTokens(),
      getAttachingAttributes(),
      getSubStyles());
  }

  //method
  @Override
  public ISelectingStyleWithSelectors withSelectorTokens(final IContainer<String> selectorTokens) {

    String optionalSelectorId = null;
    String optionalSelectorType = null;
    final var allSelectorTokens = new LinkedList<String>();

    if (hasSelectorId()) {
      optionalSelectorId = getSelectorId();
    }

    if (hasSelectorType()) {
      optionalSelectorType = getSelectorType();
    }

    allSelectorTokens.addAtEnd(getSelectorTokens());
    allSelectorTokens.addAtEnd(selectorTokens);

    return //
    new SelectingStyle(
      optionalSelectorId,
      optionalSelectorType,
      getSelectorRoles(),
      allSelectorTokens,
      getAttachingAttributes(),
      getSubStyles());
  }

  //method
  @Override
  public ISelectingStyleWithSelectors withSelectorType(final String selectorType) {

    GlobalValidator.assertThat(selectorType).thatIsNamed("selector type").isNotBlank();

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
}
