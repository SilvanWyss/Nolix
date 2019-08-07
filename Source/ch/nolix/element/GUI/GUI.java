//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.skillAPI.Recalculable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.CursorIcon;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseGUI_API.IBaseGUI;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 240
 * @param <G> The type of a {@link GUI}.
 */
public abstract class GUI<G extends GUI<G>> extends ConfigurationElement<G>
implements IBaseGUI<G>, ISmartObject<G>, Recalculable {
	
	//default value
	public static final String DEFAULT_TITLE = "GUI";
	
	//attribute
	private boolean closed = false;
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		PascalCaseNameCatalogue.TITLE,
		t -> setTitle(t),
		s -> s.getOneAttributeAsString(),
		t -> new DocumentNode(PascalCaseNameCatalogue.TITLE, t)
	);
	
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
	 * @throws NullArgumentException if the given visualizer is null.
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
		visualizer.noteClose();
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible() {
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
	 * @throws NullArgumentException if the given title is null.
	 * @throws InvalidArgumentException if the given title is blank.
	 */
	public final G setTitle(final String title) {
		
		Validator.suppose(title).thatIsNamed(VariableNameCatalogue.TITLE).isNotBlank();
		
		this.title.setValue(title);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return the {@link IVisualizer} of the current {@link GUI}.
	 * @throws ArgumentMissesAttributeException if the current {@link GUI} does not have a {@link IVisualizer}.
	 */
	protected final IVisualizer getRefVisualizer() {
		
		if (visualizer == null) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.VISUALIZER);
		}
		
		return visualizer;
	}
	
	//abstract method
	/**
	 * @return the height of the view area of the current {@link GUI}.
	 */
	protected abstract int getViewAreaHeight();
	
	//abstract method
	/**
	 * @return the width of the view area of the current {@link GUI}.
	 */
	protected abstract int getViewAreaWidth();
	
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
