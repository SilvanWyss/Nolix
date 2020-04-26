//package declaration
package ch.nolix.element.GUI;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Recalculable;
import ch.nolix.common.state.Visibility;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseGUI_API.IBaseGUI;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.input.Key;
import ch.nolix.element.inputDevice.KeyBoard;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link GUI} is mainly a {@link IBaseGUI} and a {@link IElement}.
 * A {@link GUI} contains so-called hard attributes like its title or content elements.
 * A so-called soft attributes of a {@link GUI} is for example the position where the cursor is on.
 * 
 * The {@link GUI.getAttributes} method of a {@link GUI} must deliver all attributes; the hard and the soft ones.
 * The reason is because when a {@link GUI} is used in a server-client application,
 * the soft attributes need to be transferred as well.
 * When the soft attributes would be refreshed on client-side only, e.g. by a mouse-move, a new version
 * of the {@link GUI} may be requested from the server without transferring the refreshed soft attributes.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 390
 * @param <G> The type of a {@link GUI}.
 */
public abstract class GUI<G extends GUI<G>> extends ConfigurationElement<G> implements IBaseGUI<G>, Recalculable {
	
	//default value
	public static final String DEFAULT_TITLE = "GUI";
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		PascalCaseNameCatalogue.TITLE,
		this::setTitle,
		BaseNode::getOneAttributeAsString,
		t -> new Node(PascalCaseNameCatalogue.TITLE, t)
	);
	
	//attribute
	private final MutableProperty<Integer> viewAreaWidth =
	new MutableProperty<>(
		"ViewAreaWidth",
		this::setViewAreaWidth,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableProperty<Integer> viewAreaHeight =
	new MutableProperty<>(
		"ViewAreaHeight",
		this::setViewAreaHeight,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableProperty<Integer> viewAreaCursorXPosition =
	new MutableProperty<>(
		"ViewAreaCursorXPosition",
		this::setViewAreaCursorXPosition,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);	
	
	//attribute
	private final MutableProperty<Integer> viewAreaCursorYPosition =
	new MutableProperty<>(
		"ViewAreaCursorYPosition",
		this::setViewAreaCursorYPosition,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attributes
	private final KeyBoard keyBoard = new KeyBoard();
	private IFrontEnd frontEnd = new LocalFrontEnd();
	private boolean closed = false;
	
	//optional attribute
	private final IVisualizer visualizer;
	
	//constructor
	/**
	 * Creates a new {@link GUI}.
	 * The {@link GUI} will be visible and have the given visualizer.
	 * 
	 * @param visible
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 */
	public GUI(final IVisualizer visualizer) {
		
		Validator.assertThat(visualizer).thatIsNamed(VariableNameCatalogue.VISUALIZER).isNotNull();
		
		this.visualizer = visualizer;
	}
	
	//constructor
	/**
	 * Creates a new {@link GUI}.
	 * The {@link GUI} will be visible according to the given visibility.
	 * 
	 * @param visibility
	 */
	public GUI(final Visibility visibility) {
		visualizer = visibility == Visibility.VISIBLE ? new FrameVisualizer() : null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {
		
		closed = true;
		
		if (isVisible()) {
			visualizer.noteClose();
		}
	}
	
	//method declaration
	/**
	 * @return the cursor icon of the current {@link GUI}.
	 */
	public abstract CursorIcon getCursorIcon();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getTitle() {
		return title.getValue();
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the view area of the current {@link GUI}.
	 */
	public int getViewAreaCursorXPosition() {
		return viewAreaCursorXPosition.getValue();
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the view area of the current {@link GUI}.
	 */
	public int getViewAreaCursorYPosition() {
		return viewAreaCursorYPosition.getValue();
	}
	
	//method
	/**
	 * @return the height of the view area of the current {@link GUI}.
	 */
	public int getViewAreaHeight() {
		return viewAreaHeight.getValue();
	}
	
	//method
	/**
	 * @return the width of the view area of the current {@link GUI}.
	 */
	public int getViewAreaWidth() {
		return viewAreaWidth.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() {
		return closed;
	}
	
	//method
	/**
	 * @param key
	 * @return true if the given key is pressed on the current {@link GUI}.
	 */
	public final boolean isPressed(final Key key) {
		return keyBoard.isPressed(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isVisible() {
		return (visualizer != null);
	}
	
	//method
	/**
	 * @return the {@link IFrontEnd} of the current {@link GUI}.
	 */
	public final IFrontEnd onFrontEnd() {
		return frontEnd;
	}
	
	//method declaration
	/**
	 * Paints the current {@link GUI} using the given painter.
	 * 
	 * @param painter
	 */
	public abstract void paint(IPainter painter);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void refresh() {
		recalculate();
		repaint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public G reset() {
		
		super.reset();
		
		setTitle(DEFAULT_TITLE);
		setViewAreaWidth(0);
		setViewAreaHeight(0);
		setViewAreaCursorXPosition(0);
		setViewAreaCursorYPosition(0);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the frontend of the current {@link GUI}.
	 * 
	 * @param frontEnd
	 * @throws ArgumentIsNullException if the given frontEnd is null.
	 */
	public final void setFrontEnd(final IFrontEnd frontEnd) {
		
		Validator.assertThat(frontEnd).thatIsNamed("front end").isNotNull();
		
		this.frontEnd = frontEnd;
	}
	
	//method
	/**
	 * Sets the title of the current GUI.
	 * 
	 * @param title
	 * @return the current {@link GUI}.
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws InvalidArgumentException if the given title is blank.
	 */
	public final G setTitle(final String title) {
		
		Validator.assertThat(title).thatIsNamed(VariableNameCatalogue.TITLE).isNotBlank();
		
		this.title.setValue(title);
		
		return asConcrete();
	}
	
	//method
	/**
	 * @return true if shift is locked on the current {@link GUI}.
	 */
	public final boolean shiftIsLocked() {
		return keyBoard.shiftIsLocked();
	}
	
	//method
	/**
	 * @return the {@link KeyBoard} of the current {@link GUI}.
	 */
	protected final KeyBoard getRefKeyBoard() {
		return keyBoard;
	}
	
	//method
	/**
	 * @return the {@link IVisualizer} of the current {@link GUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link GUI} does not have a {@link IVisualizer}.
	 */
	protected final IVisualizer getRefVisualizer() {
		
		if (visualizer == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.VISUALIZER);
		}
		
		return visualizer;
	}
	
	//method
	/**
	 * Initializes the current {@link GUI}.
	 */
	protected final void initialize() {
		
		reset();
		
		if (isVisible()) {
			visualizer.initialize(this);
		}
	}
	
	//method
	/**
	 * Sets the height of the view area of the current {@link GUI}.
	 * 
	 * @param viewAreaHeight
	 * @throws NegativeArgumentException if the given viewAreaHeight is negative.
	 */
	protected void setViewAreaHeight(final int viewAreaHeight) {
		
		Validator.assertThat(viewAreaHeight).thatIsNamed("view area height").isNotNegative();
		
		this.viewAreaHeight.setValue(viewAreaHeight);
	}
	
	//method
	/**
	 * Sets the width of the view area of the current {@link GUI}.
	 * 
	 * @param viewAreaWidth
	 * @throws NegativeArgumentException if the given viewAreaWidth is negative.
	 */
	protected void setViewAreaWidth(final int viewAreaWidth) {
		
		Validator.assertThat(viewAreaWidth).thatIsNamed("view area width").isNotNegative();
		
		this.viewAreaWidth.setValue(viewAreaWidth);
	}
	
	//method
	/**
	 * Sets the x-position of the cursor on the view area of the current {@link GUI}.
	 * 
	 * @param viewAreaCursorXPosition
	 */
	protected void setViewAreaCursorXPosition(final int viewAreaCursorXPosition) {
		this.viewAreaCursorXPosition.setValue(viewAreaCursorXPosition);
	}
	
	//method
	/**
	 * Setst the y-position of the cursor on the view area of the current {@link GUI}.
	 * 
	 * @param viewAreaCursorYPosition
	 */
	protected void setViewAreaCursorYPosition(final int viewAreaCursorYPosition) {
		this.viewAreaCursorYPosition.setValue(viewAreaCursorYPosition);
	}
	
	//method
	/**
	 * Repaints the current {@link GUI}.
	 */
	private void repaint() {
		if (isVisible()) {
			visualizer.repaint();
		}
	}
}
