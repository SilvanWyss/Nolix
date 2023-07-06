//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;

//class
public final class LinkTest extends ControlTest<ILink> {
	
	//method
	@Override
	protected Link createTestUnit() {
		return new Link();
	}
}
