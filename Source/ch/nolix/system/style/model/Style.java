package ch.nolix.system.style.model;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.style.model.ISelectingStyle;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 * @version 2016-02-01
 */
public final class Style extends AbstractStyle<IStyle> implements IStyle {

  /**
   * Creates a new empty {@link Style}.
   */
  public Style() {
    super(ImmutableList.createEmpty(), ImmutableList.createEmpty());
  }

  /**
   * Creates a new {@link Style}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  public Style(
    final IContainer<String> attachingAttributes,
    final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    super(attachingAttributes, subStyles);
  }

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

  /**
   * @param specification
   * @return a new {@link Style} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static Style fromSpecification(final INode<?> specification) {
    final ILinkedList<String> attachingAttributes = LinkedList.createEmpty();
    final ILinkedList<AbstractSelectingStyle> subStyles = LinkedList.createEmpty();

    for (final var a : specification.getStoredChildNodes()) {
      switch (a.getHeader()) {
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

    return new Style(attachingAttributes, subStyles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return //
    ContainerView.forIterable(
      getAttachingAttributes().to(a -> Node.withHeaderAndChildNode(ATTACHING_ATTRIBUTE_HEADER, a)),
      getSubStyles().to(ISelectingStyle::getSpecification));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void applyToElement(final IStylableElement<?> element) {
    setAttachingAttributesToElement(element);
    letSubStylesStyleChildElementsOfElement(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withAttachingAttributes(final IContainer<String> attachingAttributes) {
    final var allAttachingAttributes = ContainerView.forIterable(getAttachingAttributes(), attachingAttributes);

    return new Style(allAttachingAttributes, getSubStyles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withNewAttachingAttributesWhereSelectorType(
    final String selectorType,
    final IContainer<String> newAttachingAttributes) {
    final var subStylesWtihNewAttachingAttribtues = //
    getSubStyles().to(ss -> ss.withNewAttachingAttributesWhereSelectorType(selectorType, newAttachingAttributes));

    return new Style(getAttachingAttributes(), subStylesWtihNewAttachingAttribtues);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withStyle(final IStyle style) {
    return withAttachingAttributes(style.getAttachingAttributes()).withSubStyles(style.getSubStyles());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withSubStyles(final IContainer<? extends ISelectingStyleWithSelectors> subStyles) {
    final ILinkedList<ISelectingStyleWithSelectors> allSubStyles = LinkedList.createEmpty();
    allSubStyles.addAtEnd(getSubStyles());
    allSubStyles.addAtEnd(subStyles);

    return new Style(getAttachingAttributes(), allSubStyles);
  }
}
