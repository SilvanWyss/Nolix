package ch.nolix.system.webapplication.base;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;

final class FrontendWriter implements IFrontEndWriter {
  private final AbstractWebClient<?, ?> parentBackendWebClient;

  private FrontendWriter(final AbstractWebClient<?, ?> parentBackendWebClient) {
    Validator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  public static FrontendWriter forBackendWebClient(
    final AbstractWebClient<?, ?> backendWebClient) {
    return new FrontendWriter(backendWebClient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void openNewTabWithUrl(final String url) {
    parentBackendWebClient.internalOpenNewTabOnCounterpartWithUrl(url);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redirectTo(final IApplicationInstanceTarget applicationInstanceTarget) {
    parentBackendWebClient.internalRedirectCounterpartTo(applicationInstanceTarget);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redirectToUrl(final String url) {
    parentBackendWebClient.internalRedirectCounterpartToUrl(url);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveFile(final byte[] bytes) {
    parentBackendWebClient.internalSaveFileOnCounterpart(bytes);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void writeTextToClipboard(final String text) {
    parentBackendWebClient.internalWriteTextToClipboardOfCounterpart(text);
  }
}
