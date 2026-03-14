/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level1server;

import ch.nolix.base.commontypetool.inputstreamtool.InputStreamTool;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.base.programcontrol.worker.AbstractWorker;
import ch.nolix.baseapi.commontypetool.inputstreamtool.IInputStreamTool;

/**
 * @author Silvan Wyss
 */
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

  /**
   * {@inheritDoc}
   */
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
