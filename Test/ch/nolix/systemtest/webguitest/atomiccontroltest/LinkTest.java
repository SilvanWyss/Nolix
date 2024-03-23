//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;
import ch.nolix.systemtest.webguitest.maintest.ControlTest;

//class
final class LinkTest extends ControlTest<ILink> {

  //method
  @Override
  protected Link createTestUnit() {
    return new Link();
  }
}
