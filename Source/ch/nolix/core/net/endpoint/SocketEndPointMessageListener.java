package ch.nolix.core.net.endpoint;

import ch.nolix.core.commontypetool.inputstreamtool.InputStreamTool;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.worker.Worker;
import ch.nolix.coreapi.commontypetoolapi.inputstreamtoolapi.IInputStreamTool;

final class SocketEndPointMessageListener extends Worker {

  private static final IInputStreamTool INPUT_STREAM_TOOL = new InputStreamTool();

  private final SocketEndPoint parentNetEndPoint;

  public SocketEndPointMessageListener(final SocketEndPoint parentNetEndPoint) {

    GlobalValidator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();

    this.parentNetEndPoint = parentNetEndPoint;

    start();
  }

  @Override
  protected void run() {
    while (parentNetEndPoint.isOpen()) {

      final var line = INPUT_STREAM_TOOL.readLineFromInputStream(parentNetEndPoint.getStoredInputStream());

      if (line == null) {
        parentNetEndPoint.close();
        break;
      }

      parentNetEndPoint.receiveRawMessageInBackground(line);
    }
  }
}
