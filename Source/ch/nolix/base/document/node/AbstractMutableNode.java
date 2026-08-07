/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.node;

import ch.nolix.base.environment.filesystem.FileAccessor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <N> the type of a {@link AbstractMutableNode}.
 */
public abstract class AbstractMutableNode<N extends AbstractMutableNode<N>>
extends AbstractNode<N>
implements IMutableNode<N> {
  /**
   * {@inheritDoc}
   */
  @Override
  public final N addPostfixToHeader(final String postfix) {
    // Asserts that the given postfix is not blank.
    Validator.assertThat(postfix).thatIsNamed(LowerCaseVariableNameCatalog.POSTFIX).isNotBlank();

    // Handles the case that the current BaseMutableNode does not have a header.
    if (!hasHeader()) {
      setHeader(postfix);

      // Handles the case that the current BaseMutableNode has a header.
    } else {
      setHeader(getHeader() + postfix);
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N addPrefixToHeader(final String prefix) {
    // Asserts that the given prefix is not blank.
    Validator.assertThat(prefix).thatIsNamed(LowerCaseVariableNameCatalog.PREFIX).isNotBlank();

    // Handles the case that the current BaseMutableNode does not have a header.
    if (!hasHeader()) {
      setHeader(prefix);

      // Handles the case that the current BaseMutableNode has a header.
    } else {
      setHeader(prefix + getHeader());
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void resetFromFile(final String filePath) {
    resetFromString(
      FileAccessor.withFilePath(filePath)
        .readFile()
        .replace(String.valueOf(CharacterCatalog.TABULATOR), StringCatalog.EMPTY_STRING)
        .replace(String.valueOf(CharacterCatalog.NEW_LINE), StringCatalog.EMPTY_STRING));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void resetFromNode(Node<?> node) {
    reset();

    if (node.hasHeader()) {
      setHeader(node.getHeader());
    }

    addChildNodes(node.getStoredChildNodes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void resetFromString(final String string) {
    MutableNodeParser.resetMutableNodeFromString(this, string);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final N setChildNodes(final Iterable<? extends Node<?>> childNodes) {
    removeChildNodes();
    addChildNodes(childNodes);

    return asConcrete();
  }

  /**
   * @return the current {@link AbstractMutableNode}.
   */
  protected abstract N asConcrete();
}
