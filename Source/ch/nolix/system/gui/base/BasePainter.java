//package declaration
package ch.nolix.system.gui.base;

import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
public abstract class BasePainter implements IPainter {
	
	//optional attribute
	private final IPainter parentPainter;
	
	//constructor
	public BasePainter(final SingleContainer<IPainter> parentPainterContainer) {
		if (parentPainterContainer.isEmpty()) {
			parentPainter = null;
		} else {
			parentPainter = parentPainterContainer.getRefElement();
		}
	}
	
	//method
	@Override
	public final boolean descendsFromOtherPainter() {
		return (parentPainter != null);
	}
	
	//method
	@Override
	public double getEffectiveOpacity() {
		
		if (!descendsFromOtherPainter()) {
			return getOpacity();
		}
		
		return (getParentPainter().getEffectiveOpacity() * getOpacity());
	}
	
	//method
	@Override
	public IPainter getParentPainter() {
		
		assertDescendsFromOtherPainter();
		
		return parentPainter;
	}
	
	//method
	private void assertDescendsFromOtherPainter() {
		if (!descendsFromOtherPainter()) {
			throw new ArgumentDoesNotBelongToParentException(this, IPainter.class);
		}
	}
}
