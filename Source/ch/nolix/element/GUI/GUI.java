//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Recalculable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseGUI_API.IBaseGUI;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.input.Key;
import ch.nolix.element.input.KeyBoard;
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 260
 * @param <G> The type of a {@link GUI}.
 */
public abstract class GUI<G extends GUI<G>> extends ConfigurationElement<G>
implements IBaseGUI<G>, ISmartObject<G>, Recalculable {
	
	//default value
	public static final String DEFAULT_TITLE = "GUI";
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		PascalCaseNameCatalogue.TITLE,
		t -> setTitle(t),
		s -> s.getOneAttributeAsString(),
		t -> new Node(PascalCaseNameCatalogue.TITLE, t)
	);
	
	//attributes
	private final KeyBoard keyBoard = new KeyBoard();
	private boolean closed = false;
	
	//optional attribute
	private final IVisualizer visualizer;
	
	//constructor
	/**
	 * Creates a new {@link GUI}.
	 * The {@link GUI} will be visible if the given visible flag is true.
	 * 
	 * @param visible
	 */
	public GUI(final boolean visible) {
		visualizer = visible ? new FrameVisualizer() : null;
	}
	
	//constructor
	/**
	 * Creates a new {@link GUI}.
	 * The {@link GUI} will be visible and have the given visualizer.
	 * 
	 * @param visible
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 */
	public GUI(final IVisualizer visualizer) {
		
		Validator.suppose(visualizer).thatIsNamed(VariableNameCatalogue.VISUALIZER).isNotNull();
		
		this.visualizer = visualizer;
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
	
	//abstract method
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
		return getRefKeyBoard().isPressed(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isVisible() {
		return (visualizer != null);
	}
	
	//abstract method
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
		
		return asConcreteType();
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
		
		Validator.suppose(title).thatIsNamed(VariableNameCatalogue.TITLE).isNotBlank();
		
		this.title.setValue(title);
		
		return asConcreteType();
	}
	
	//abstract method
	/**
	 * @return the height of the view area of the current {@link GUI}.
	 */
	public abstract int getViewAreaHeight();
	
	//abstract method
	/**
	 * @return the width of the view area of the current {@link GUI}.
	 */
	public abstract int getViewAreaWidth();
	
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

	//abstract method
	/**
	 * @return the x-position of the cursor on the view area of the current {@link GUI}.
	 */
	protected abstract int getViewAreaCursorXPosition();
	
	//abstract method
	/**
	 * @return the y-position of the cursor on the view area of the current {@link GUI}.
	 */
	protected abstract int getViewAreaCursorYPosition();
	
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
	 * Lets the current {@link GUI} note a resizing.
	 */
	protected final void noteResizing() {
		refresh();
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
