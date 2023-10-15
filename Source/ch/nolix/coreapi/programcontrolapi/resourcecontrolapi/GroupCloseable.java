//package declaration
package ch.nolix.coreapi.programcontrolapi.resourcecontrolapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationapi.Closeable;
import ch.nolix.coreapi.functionapi.requestapi.CloseStateRequestable;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 */
@AllowDefaultMethodsAsDesignPattern
public interface GroupCloseable extends Closeable, CloseStateRequestable {

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  default void close() {
    getStoredCloseController().close();
  }

  // method
  /**
   * Creates a close dependency between the current {@link GroupCloseable} and the
   * given element.
   * 
   * When a {@link GroupCloseable} is closed, all elements, the current
   * {@link GroupCloseable} has a close dependency to, will be closed too and vice
   * versa.
   * 
   * @param element
   * @throws RuntimeException if the current {@link GroupCloseable} is already
   *                          closed.
   * @throws RuntimeException if the current {@link GroupCloseable} has already a
   *                          close dependency to the given element.
   */
  default void createCloseDependencyTo(final GroupCloseable element) {
    getStoredCloseController().createCloseDependencyTo(element);
  }

  // method declaration
  /**
   * @return the {@link ICloseController} of the current {@link GroupCloseable}.
   */
  ICloseController getStoredCloseController();

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  default boolean isClosed() {
    return getStoredCloseController().hasClosed();
  }

  // method declaration
  /**
   * Lets the current {@link GroupCloseable} note a close.
   */
  void noteClose();
}
