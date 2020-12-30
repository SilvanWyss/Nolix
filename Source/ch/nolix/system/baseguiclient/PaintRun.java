//package declaration
package ch.nolix.system.baseguiclient;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painterapi.IPainter;

//class
public final class PaintRun {
	
	//multi-attributes
	private final IContainer<IElementTaker<PaintRun>> paintCommands;
	private LinkedList<IPainter> painters = new LinkedList<>();
	
	//constructor
	public PaintRun(final IPainter painter,	final IContainer<IElementTaker<PaintRun>> paintCommands) {
		
		Validator.assertThat(paintCommands).thatIsNamed("paint commands").isNotNull();
		
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
