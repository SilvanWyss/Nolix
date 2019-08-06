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
 * @lines 10
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
	public GUI(final boolean visible) {
		if (!visible) {
			visualizer = null;
		}
		else {
			visualizer = new FrameVisualizer();
		}
	}
	
	//constructor
	public GUI(final IVisualizer visualizer) {
		
		Validator.suppose(visualizer).thatIsNamed("visualizer").isNotNull();
		
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
	@Override
	public boolean isVisible() {
		return (visualizer != null);
	}
	
	//abstract method
	public abstract void paint(IPainter painter);

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
	 * {@inheritDoc}
	 */
	@Override
	public final void refresh() {
		recalculate();
		repaint();
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note a resizing.
	 */
	protected final void noteResizing() {
		refresh();
	}
	
	//method
	protected final IVisualizer getRefVisualizer() {
		
		if (visualizer == null) {
			throw new ArgumentMissesAttributeException(this, "visualizer");
		}
		
		return visualizer;
	}
	
	//abstract method
	protected abstract int getViewAreaHeight();
	
	//abstract method
	protected abstract int getViewAreaWidth();
	
	//abstract method
	protected abstract int getViewAreaCursorXPosition();
	
	//abstract method
	protected abstract int getViewAreaCursorYPosition();
	
	//method
	protected final void initialize() {
		if (isVisible()) {
			visualizer.initialize(this);
		}
	}

	//method
	private void repaint() {
		if (isVisible()) {
			visualizer.repaint();
		}
	}
}
