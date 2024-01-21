//package declaration
package ch.nolix.core.net.messaging;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * A {@link IndexedPackage} bundles an index and a content.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 * @param <C> is the type of the content of a {@link IndexedPackage}.
 */
public class IndexedPackage<C> {

  //attribute
  private final int index;

  //attribute
  private final C content;

  //constructor
  /**
   * Creates a new {@link IndexedPackage} with the given index and content.
   * 
   * @param index
   * @param content
   * @throws ArgumentIsNullException if the given content is null.
   */
  public IndexedPackage(final int index, final C content) {

    //Asserts that the given content is not null.
    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNotNull();

    //Sets the index of the current IndexedPackage.
    this.index = index;

    //Sets the content of the current IndexedPackage.
    this.content = content;
  }

  //method
  /**
   * @return the content of the current {@link IndexedPackage}.
   */
  public final C getStoredContent() {
    return content;
  }

  //method
  /**
   * @return the index of the current {@link IndexedPackage}.
   */
  public final int getIndex() {
    return index;
  }

  //method
  /**
   * @param index
   * @return true if the current {@link IndexedPackage} has the given index.
   */
  public final boolean hasIndex(final int index) {
    return (getIndex() == index);
  }
}
