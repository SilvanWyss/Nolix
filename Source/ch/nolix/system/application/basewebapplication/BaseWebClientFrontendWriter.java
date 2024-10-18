package ch.nolix.system.application.basewebapplication;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

final class BaseWebClientFrontendWriter implements IFrontEndWriter {

  private final BaseWebClient<?, ?> parentBackendWebClient;

  private BaseWebClientFrontendWriter(final BaseWebClient<?, ?> parentBackendWebClient) {

    GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  public static BaseWebClientFrontendWriter forBackendWebClient(
    final BaseWebClient<?, ?> backendWebClient) {
    return new BaseWebClientFrontendWriter(backendWebClient);
  }

  @Override
  public void openNewTabWithUrl(final String url) {
    parentBackendWebClient.internalOpenNewTabOnCounterpartWithUrl(url);
  }

  @Override
  public void redirectTo(final IApplicationInstanceTarget applicationInstanceTarget) {
    parentBackendWebClient.internalRedirectCounterpartTo(applicationInstanceTarget);
  }

  @Override
  public void redirectToUrl(final String url) {
    parentBackendWebClient.internalRedirectCounterpartToUrl(url);
  }

  @Override
  public void saveFile(final byte[] bytes) {
    parentBackendWebClient.internalSaveFileOnCounterpart(bytes);
  }

  @Override
  public void writeTextToClipboard(final String text) {
    parentBackendWebClient.internalWriteTextToClipboardOfCounterpart(text);
  }
}
