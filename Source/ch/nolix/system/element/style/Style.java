package ch.nolix.system.element.style;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.element.styletool.AttributeReplacer;
import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.elementapi.styleapi.IAttachingAttribute;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyle;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;
import ch.nolix.systemapi.elementapi.styletoolapi.IAttributeReplacer;

/**
 * @author Silvan Wyss
 * @version 2016-02-01
 */
public final class Style extends AbstractStyle<IStyle> implements IStyle {

  private static final IAttributeReplacer ATTRIBUTE_REPLACER = new AttributeReplacer();

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
    final IContainer<? extends IAttachingAttribute> attachingAttributes,
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

    final ILinkedList<IAttachingAttribute> attachingAttributes = LinkedList.createEmpty();
    final ILinkedList<AbstractSelectingStyle> subStyles = LinkedList.createEmpty();

    for (final var a : specification.getStoredChildNodes()) {
      switch (a.getHeader()) {
        case ATTACHING_ATTRIBUTE_HEADER:
          attachingAttributes.addAtEnd(AttachingAttribute.fromSpecification(a));
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
      getAttachingAttributes().to(IElement::getSpecification),
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
  public IStyle withAttachingAttributes(final IContainer<? extends IAttachingAttribute> attachingAttributes) {

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
  public IStyle withReplacedAttachingAttributes(
    final IContainer<IPair<String, String>> attachingAttributeReplacements) {

    final var replacedAttachingAttributes = //
    ATTRIBUTE_REPLACER.getReplacedAttributesFromAttributesAndAttributeReplacements(
      getAttachingAttributes(),
      attachingAttributeReplacements);

    final var subStylesWithReplacedAttachingAttributes = //
    getSubStyles().to(ss -> ss.withReplacedAttachingAttributes(attachingAttributeReplacements));

    return new Style(replacedAttachingAttributes, subStylesWithReplacedAttachingAttributes);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withReplacedTaggedAttachingAttributes(
    final IContainer<IPair<Enum<?>, String>> attachingAttributeReplacements) {

    final var replacedAttachingAttributes = //
    ATTRIBUTE_REPLACER.getReplacedTaggedAttributesFromAttributesAndAttributeReplacements(
      getAttachingAttributes(),
      attachingAttributeReplacements);

    final var subStylesWithReplacedAttachingAttributes = //
    getSubStyles().to(ss -> ss.withReplacedTaggedAttachingAttributes(attachingAttributeReplacements));

    return new Style(replacedAttachingAttributes, subStylesWithReplacedAttachingAttributes);
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
