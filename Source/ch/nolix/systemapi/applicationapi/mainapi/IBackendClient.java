package ch.nolix.systemapi.applicationapi.mainapi;

/**
 * @author Silvan Wyss
 * @version 2025-07-11
 * @param <S> is the type of the application service of the parent application
 *            of a {@link IBackendClient}.
 */
public interface IBackendClient<S> extends IClient {

  /**
   * @return the application service of the parent application of the current
   *         {@link IBackendClient}.
   */
  S getStoredApplicationService();
}
