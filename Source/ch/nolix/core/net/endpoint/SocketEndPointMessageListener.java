//package declaration
package ch.nolix.core.net.endpoint;

import ch.nolix.core.commontypetool.commontypehelper.GlobalInputStreamHelper;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.worker.Worker;

//class
final class SocketEndPointMessageListener extends Worker {

  //attribute
  private final SocketEndPoint parentNetEndPoint;

  //constructor
  public SocketEndPointMessageListener(final SocketEndPoint parentNetEndPoint) {

    GlobalValidator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();

    this.parentNetEndPoint = parentNetEndPoint;

    start();
  }

  //method
  @Override
  protected void run() {
    while (parentNetEndPoint.isOpen()) {

      final var line = GlobalInputStreamHelper.readLineFrom(parentNetEndPoint.getStoredInputStream());

      if (line == null) {
        parentNetEndPoint.close();
        break;
      }

      parentNetEndPoint.receiveRawMessageInBackground(line);
    }
  }
}
