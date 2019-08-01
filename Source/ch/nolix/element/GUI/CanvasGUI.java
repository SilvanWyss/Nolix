//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.CursorIcon;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.baseGUI_API.IEventTaker;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
public abstract class CanvasGUI<CG extends CanvasGUI<CG>> extends GUI<CG> {
	
	//attribute
	private CursorIcon cursorIcon;
	
	//attribute
	private final IEventTaker eventTaker;
	
	//constructor
	public CanvasGUI(final IEventTaker eventTaker, final boolean visible) {
		
		super(visible);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		this.eventTaker = eventTaker;
	}
	
	//constructor
	public CanvasGUI(final IEventTaker eventTaker, final IVisualizer visualizer) {
		
		super(visualizer);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		this.eventTaker = eventTaker;
	}
	
	//method
	@Override
	public final boolean containsElement(final String name) {
		return false;
	}
	
	//method
	@Override
	public CursorIcon getCursorIcon() {
		return cursorIcon;
	}
	
	//method
	@Override
	public final IContainer<IConfigurableElement<?>> getRefConfigurables() {
		return new List<>();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyPress(final Key key) {
		eventTaker.noteKeyPress(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyRelease(final Key key) {
		eventTaker.noteKeyRelease(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyTyping(final Key key) {
		eventTaker.noteKeyTyping(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonClick() {
		eventTaker.noteLeftMouseButtonClick();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonPress() {
		eventTaker.noteLeftMouseButtonPress();
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonRelease() {
		eventTaker.noteLeftMouseButtonRelease();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		eventTaker.noteMouseMove(cursorXPosition, cursorYPosition);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelClick() {
		eventTaker.noteMouseWheelClick();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelPress() {
		eventTaker.noteMouseWheelPress();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRelease() {
		eventTaker.noteMouseWheelRelease();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRotationStep(DirectionOfRotation directionOfRotation) {
		eventTaker.noteMouseWheelRotationStep(directionOfRotation);		
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		eventTaker.noteResize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		eventTaker.noteRightMouseButtonClick();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonPress() {
		eventTaker.noteRightMouseButtonPress();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonRelease() {
		eventTaker.noteRightMouseButtonRelease();		
	}
	
	//method
	@Override
	public CG resetConfiguration() {
		return asConcreteType();
	}
	
	//method
	@Override
	public final void paint(final IPainter painter) {
		//TODO
	}
	
	//method
	@Override
	public final void recalculate() {}
}
