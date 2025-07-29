package ch.nolix.coreapi.resourcecontrol.closecontroller;

import ch.nolix.coreapi.structure.typemarker.AllowDefaultMethodsAsDesignPattern;

/**
 * @author Silvan Wyss
 * @version 2020-07-05
 */
@AllowDefaultMethodsAsDesignPattern
public interface GroupCloseable extends Closeable {

  /**
   * {@inheritDoc}
   */
  @Override
  default void close() {
    getStoredCloseController().close();
  }

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

  /**
   * @return the {@link ICloseController} of the current {@link GroupCloseable}.
   */
  ICloseController getStoredCloseController();

  /**
   * {@inheritDoc}
   */
  @Override
  default boolean isClosed() {
    return getStoredCloseController().hasClosed();
  }

  /**
   * Lets the current {@link GroupCloseable} note a close.
   */
  void noteClose();
}
