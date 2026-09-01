/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.messaging;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link AbstractIndexedPackage} bundles an index and a content.
 * 
 * @author Silvan Wyss
 * @param <C> the type of the content of a {@link AbstractIndexedPackage}.
 */
public abstract class AbstractIndexedPackage<C> {
  private final int memberIndex;

  private final C content;

  /**
   * Creates a new {@link AbstractIndexedPackage} with the given index and
   * content.
   * 
   * @param index
   * @param content
   * @throws RuntimeException if the given content is null
   */
  protected AbstractIndexedPackage(final int index, final C content) {
    // Asserts that the given content is not null.
    Validator.assertThat(content).thatIsNamed(LowerCaseVariableNameCatalog.CONTENT).isNotNull();

    // Sets the index of the current IndexedPackage.
    memberIndex = index;

    // Sets the content of the current IndexedPackage.
    this.content = content;
  }

  /**
   * @return the content of the current {@link AbstractIndexedPackage}.
   */
  public final C getStoredContent() {
    return content;
  }

  /**
   * @return the index of the current {@link AbstractIndexedPackage}.
   */
  public final int getIndex() {
    return memberIndex;
  }

  /**
   * @param index
   * @return true if the current {@link AbstractIndexedPackage} has the given
   *         index, false otherwise
   */
  public final boolean hasIndex(final int index) {
    return (getIndex() == index);
  }
}
