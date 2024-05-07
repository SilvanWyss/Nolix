//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyle;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;

//class
/**
 * @author Silvan Wyss
 * @date 2016-02-01
 */
public final class Style extends BaseStyle<IStyle> implements IStyle {

  //constructor
  /**
   * Creates a new empty {@link Style}.
   */
  public Style() {
    super(new ImmutableList<>(), new ImmutableList<>());
  }

  //constructor
  /**
   * Creates a new {@link Style}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  public Style(
    final IContainer<? extends INode<?>> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    super(attachingAttributes, subStyles);
  }

  //static method
  /**
   * @param filePath
   * @return a new standard specification from the file with the given file path.
   * @throws InvalidArgumentException if the given file path is not valid.
   * @throws InvalidArgumentException if the file with the given file path does
   *                                  not represent a standard configuration.
   */
  public static Style fromFile(final String filePath) {

    final var specification = Node.fromFile(filePath);

    return fromSpecification(specification);
  }

  //static method
  /**
   * @param specification
   * @return a new {@link Style} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static Style fromSpecification(final INode<?> specification) {

    final var attachingAttributes = new LinkedList<INode<?>>();
    final var subStyles = new LinkedList<BaseSelectingStyle>();

    for (final var a : specification.getStoredChildNodes()) {
      switch (a.getHeader()) {
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

    return new Style(attachingAttributes, subStyles);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return ReadContainer.forIterable(
      getAttachingAttributes().to(a -> Node.withHeaderAndChildNode(ATTACHING_ATTRIBUTE_HEADER, a)),
      getSubStyles().to(ISelectingStyle::getSpecification));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void applyToElement(final IStylableElement<?> element) {
    setAttachingAttributesToElement(element);
    letSubStylesStyleChildElementsOfElement(element);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withAttachingAttributes(final IContainer<String> attachingAttributes) {

    final var allAttachingAttributes = new LinkedList<Node>();

    for (final var aa : getAttachingAttributes()) {
      allAttachingAttributes.addAtEnd(Node.fromNode(aa));
    }

    for (final var aa : attachingAttributes) {
      allAttachingAttributes.addAtEnd(Node.fromString(aa));
    }

    return new Style(allAttachingAttributes, getSubStyles());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withSubStyles(final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {

    final var allSubStyles = new LinkedList<ISelectingStyleWithSelectors>();
    allSubStyles.addAtEnd(getSubStyles());
    allSubStyles.addAtEnd(subStyles);

    return new Style(getAttachingAttributes(), allSubStyles);
  }
}
