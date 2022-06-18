//package declaration
package ch.nolix.system.gui.painter;

//own imports
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2022-06-18
 */
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IPainter createPainter() {
		return createPainter(0, 0);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean descendsFromOtherPainter() {
		return (parentPainter != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final double getEffectiveOpacity() {
		
		if (!descendsFromOtherPainter()) {
			return getOpacity();
		}
		
		return (getParentPainter().getEffectiveOpacity() * getOpacity());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IPainter getParentPainter() {
		
		assertDescendsFromOtherPainter();
		
		return parentPainter;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void paintFilledRectangle(final int width, final int height) {
		paintFilledRectangle(0, 0, width, height);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void paintText(final String text) {
		
		//Calls other method.
		paintText(text, getDefaultTextFormat());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void paintText(final String text, final int maxWidth) {
		
		//Calls other method.
		paintText(text, getDefaultTextFormat(), maxWidth);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void translateHorizontally(final int xTranslation) {
		translate(xTranslation, 0);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void translateVertically(final int yTranslation) {
		translate(0, yTranslation);
	}
	
	//method
	private void assertDescendsFromOtherPainter() {
		if (!descendsFromOtherPainter()) {
			throw new ArgumentDoesNotBelongToParentException(this, IPainter.class);
		}
	}
}
