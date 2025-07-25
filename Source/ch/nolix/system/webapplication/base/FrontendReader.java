package ch.nolix.system.webapplication.base;

import java.util.Optional;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;

final class FrontendReader implements IFrontEndReader {

  private final AbstractWebClient<?, ?> parentBackendWebClient;

  private FrontendReader(final AbstractWebClient<?, ?> parentBackendWebClient) {

    Validator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  public static FrontendReader forBackendWebClient(
    final AbstractWebClient<?, ?> backendWebClient) {
    return new FrontendReader(backendWebClient);
  }

  @Override
  public IContainer<byte[]> getFilesFromClipboard() {
    return parentBackendWebClient.internalGetFilesFromClipboardOfCounterpart();
  }

  @Override
  public String getTextFromClipboard() {
    return parentBackendWebClient.internalGetTextFromClipboardOfCounterpart();
  }

  @Override
  public Optional<byte[]> readFileToBytes() {
    return parentBackendWebClient.internalReadOptionalFileFromCounterpart();
  }
}
