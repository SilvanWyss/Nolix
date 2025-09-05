package ch.nolix.core.net.messaging;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link IndexedPackage} bundles an index and a content.
 * 
 * @author Silvan Wyss
 * @version 2016-06-01
 * @param <C> is the type of the content of a {@link IndexedPackage}.
 */
public class IndexedPackage<C> {
  private final int index;

  private final C content;

  /**
   * Creates a new {@link IndexedPackage} with the given index and content.
   * 
   * @param index
   * @param content
   * @throws ArgumentIsNullException if the given content is null.
   */
  protected IndexedPackage(final int index, final C content) {
    //Asserts that the given content is not null.
    Validator.assertThat(content).thatIsNamed(LowerCaseVariableCatalog.CONTENT).isNotNull();

    //Sets the index of the current IndexedPackage.
    this.index = index;

    //Sets the content of the current IndexedPackage.
    this.content = content;
  }

  /**
   * @param index
   * @param content
   * @param <C2>    is the type of the given content.
   * @return a new {@link IndexedPackage} with the given index and content.
   * @throws ArgumentIsNullException if the given content is null.
   */
  public static <C2> IndexedPackage<C2> withIndexAndContent(final int index, final C2 content) {
    return new IndexedPackage<>(index, content);
  }

  /**
   * @return the content of the current {@link IndexedPackage}.
   */
  public final C getStoredContent() {
    return content;
  }

  /**
   * @return the index of the current {@link IndexedPackage}.
   */
  public final int getIndex() {
    return index;
  }

  /**
   * @param index
   * @return true if the current {@link IndexedPackage} has the given index.
   */
  public final boolean hasIndex(final int index) {
    return (getIndex() == index);
  }
}
