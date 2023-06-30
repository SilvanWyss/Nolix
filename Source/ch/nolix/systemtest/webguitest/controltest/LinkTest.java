//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.system.webgui.control.Link;
import ch.nolix.systemapi.webguiapi.controlapi.ILink;

//class
public final class LinkTest extends ControlTest<ILink> {
	
	//method
	@Override
	protected Link createTestUnit() {
		return new Link();
	}
}
