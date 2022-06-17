//package declaration
package ch.nolix.system.application.baseguiapplication;

import ch.nolix.core.container.main.LinkedList;
//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
public final class PaintRun {
	
	//multi-attributes
	private final IContainer<IElementTaker<PaintRun>> paintCommands;
	private LinkedList<IPainter> painters = new LinkedList<>();
	
	//constructor
	public PaintRun(final IPainter painter,	final IContainer<IElementTaker<PaintRun>> paintCommands) {
		
		GlobalValidator.assertThat(paintCommands).thatIsNamed("paint commands").isNotNull();
		
		this.paintCommands = paintCommands;
		addPainter(painter);
		
		run();
	}
	
	//method
	public void addPainter(final IPainter painter) {
		painters.addAtEnd(painter);
	}
	
	//method
	public IPainter getRefPainterByIndex(final int index) {
		return painters.getRefAt(index);
	}
	
	//method
	private void run() {
		paintCommands.forEach(pc -> pc.run(this));
	}
}
