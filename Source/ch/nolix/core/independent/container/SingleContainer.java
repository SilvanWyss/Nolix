//package declaration
package ch.nolix.core.independent.container;

//class
/**
 * A {@link SingleContainer} is empty or stores 1 element. A
 * {@link SingleContainer} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2020-03-09
 * @param <E> is the type of the element of a {@link SingleContainer}.
 */
public final class SingleContainer<E> {

  // optional attribute
  private final E element;

  // constructor
  /**
   * Creates a new {@link SingleContainer} that is empty.
   */
  public SingleContainer() {
    element = null;
  }

  // constructor
  /**
   * Creates a new {@link SingleContainer} with the given element.
   * 
   * @param element
   * @throws IllegalArgumentException if the given element is null.
   */
  public SingleContainer(final E element) {

    // Asserts that the given element is not null.
    if (element == null) {
      throw new IllegalArgumentException("The given element is null.");
    }

    // Sets the element of the current SingleContainer.
    this.element = element;
  }

  // method
  /**
   * @return the element of the current {@link SingleContainer}.
   * @throws IllegalStateException if the current {@link SingleContainer} is
   *                               empty.
   */
  public E getStoredElement() {

    // Asserts that the current SingleContainer is not empty.
    if (isEmpty()) {
      throw new IllegalStateException("The current SingleContainer is empty.");
    }

    // Returns the element of the current SingleContainer.
    return element;
  }

  // method
  /**
   * @return true if the current {@link SingleContainer} is empty.
   */
  public boolean isEmpty() {
    return (element == null);
  }
}
