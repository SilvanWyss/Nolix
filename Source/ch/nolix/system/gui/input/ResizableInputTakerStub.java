//package declaration
package ch.nolix.system.gui.input;

//own imports
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;

//class
public final class ResizableInputTakerStub extends InputTakerStub implements IResizableInputTaker {
	
	//method
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		//Does nothing.
	}
}
