//package declaration
package ch.nolix.core.duplexController;

//own import
import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.container.List;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 10
 */
public class Server extends AbortableElement {
	
	//attribute
	private final List<IDuplexControllerTaker> duplexControllerTaker
	= new List<IDuplexControllerTaker>();

	//method
	/**
	 * Lets this server note an abort.
	 */
	protected void noteAbort() {}
	
	public void addDuplexControllerTaker(final IDuplexControllerTaker duplexControllerTaker) {
		this.duplexControllerTaker.addAtEnd(duplexControllerTaker);
	}
	
	public void takeDuplexController(final DuplexController duplexController) {
		final String target = duplexController.getTarget();
		duplexControllerTaker.getRefFirst(dct -> dct.hasName(target)).takeDuplexController(duplexController);
	}
}
