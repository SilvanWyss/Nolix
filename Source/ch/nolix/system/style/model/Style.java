/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.model;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.style.model.ISelectingStyle;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.systemapi.style.stylable.StylableElement;

/**
 * @author Silvan Wyss
 */
public final class Style extends AbstractStyle<IStyle> implements IStyle {
  public static final Style EMPTY = new Style();

  /**
   * Creates a new empty {@link Style}.
   */
  private Style() {
    super(ImmutableList.createEmpty(), ImmutableList.createEmpty());
  }

  /**
   * Creates a new {@link Style}.
   * 
   * @param attachingAttributes
   * @param subStyles
   */
  private Style(
    final ExtendedIterable<String> attachingAttributes,
    final ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles) {
    super(attachingAttributes, subStyles);
  }

  /**
   * @param filePath
   * @return a new standard specification from the file with the given file path
   * @throws RuntimeException if the given file path is not valid
   * @throws RuntimeException if the file with the given file path does not
   *                          represent a standard configuration.
   */
  public static Style fromFile(final String filePath) {
    final var specification = ImmutableNode.fromFile(filePath);

    return fromSpecification(specification);
  }

  /**
   * @param specification
   * @return a new {@link Style} from the given specification
   * @throws RuntimeException if the given specification is not valid
   */
  public static Style fromSpecification(final Node<?> specification) {
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
          InvalidArgumentException.forArgumentAndArgumentName(
            specification,
            LowerCaseVariableNameCatalog.SPECIFICATION);
      }
    }

    return new Style(attachingAttributes, subStyles);
  }

  /**
   * @param attachingAttributes
   * @param subStyles
   * @return a new {@link Style} with the given attachingAttributes and subStyles.
   */
  public static Style withAttachingAttributesAndSubStyles(
    final ExtendedIterable<String> attachingAttributes,
    final ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles) {
    return new Style(attachingAttributes, subStyles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> getAttributes() {
    return //
    ExtendedIterableView.forIterables(
      getAttachingAttributes().getViewOf(a -> ImmutableNode.withHeaderAndChildNode(ATTACHING_ATTRIBUTE_HEADER, a)),
      getSubStyles().getViewOf(ISelectingStyle::getSpecification));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void applyToElement(final StylableElement<?> element) {
    setAttachingAttributesToElement(element);
    letSubStylesStyleChildElementsOfElement(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStyle withAttachingAttributes(final ExtendedIterable<String> attachingAttributes) {
    final var allAttachingAttributes = ExtendedIterableView.forIterables(getAttachingAttributes(), attachingAttributes);

    return new Style(allAttachingAttributes, getSubStyles());
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
  public IStyle withSubStyles(final ExtendedIterable<? extends ISelectingStyleWithSelectors> subStyles) {
    final ILinkedList<ISelectingStyleWithSelectors> allSubStyles = LinkedList.createEmpty();
    allSubStyles.addAtEnd(getSubStyles());
    allSubStyles.addAtEnd(subStyles);

    return new Style(getAttachingAttributes(), allSubStyles);
  }
}
