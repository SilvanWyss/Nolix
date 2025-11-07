package ch.nolix.core.net.endpoint;

import ch.nolix.core.commontypetool.inputstreamtool.InputStreamTool;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.worker.AbstractWorker;
import ch.nolix.coreapi.commontypetool.inputstreamtool.IInputStreamTool;

public final class SocketEndPointMessageListener extends AbstractWorker {
  private static final IInputStreamTool INPUT_STREAM_TOOL = new InputStreamTool();

  private final SocketEndPoint parentSocketEndPoint;

  private SocketEndPointMessageListener(final SocketEndPoint parentSocketEndPoint) {
    Validator.assertThat(parentSocketEndPoint).thatIsNamed("parent SocketEndPoint").isNotNull();

    this.parentSocketEndPoint = parentSocketEndPoint;

    start();
  }

  public static SocketEndPointMessageListener forSocketEndPoint(final SocketEndPoint parentSocketEndPoint) {
    return new SocketEndPointMessageListener(parentSocketEndPoint);
  }

  @Override
  protected void run() {
    while (parentSocketEndPoint.isOpen()) {
      final var line = INPUT_STREAM_TOOL.readLineFromInputStreamOrNull(parentSocketEndPoint.getStoredInputStream());

      if (line == null) {
        parentSocketEndPoint.close();
        break;
      }

      parentSocketEndPoint.receiveRawMessageInBackground(line);
    }
  }
}
