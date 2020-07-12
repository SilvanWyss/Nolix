//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.caching.CachingContainer;
import ch.nolix.common.closeableElement.CloseController;
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.pair.IntPair;
import ch.nolix.common.skillAPI.Recalculable;
import ch.nolix.common.state.Visibility;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseGUI_API.IBaseGUI;
import ch.nolix.element.baseGUI_API.IFrontEndReader;
import ch.nolix.element.baseGUI_API.IFrontEndWriter;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.graphic.Image;
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
 * @lines 380
 * @param <G> The type of a {@link GUI}.
 */
public abstract class GUI<G extends GUI<G>> extends ConfigurationElement<G> implements IBaseGUI<G>, Recalculable {
	
	//constant
	public static final String DEFAULT_TITLE = "GUI";
	
	//constants
	private static final String VIEW_AREA_SIZE_HEADER = "ViewAreaSize";
	private static final String VIEW_AREA_CURSOR_POSITION_HEADER = "ViewAreaCursorPosition";
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		PascalCaseNameCatalogue.TITLE,
		this::setTitle,
		BaseNode::getOneAttributeAsString,
		t -> new Node(PascalCaseNameCatalogue.TITLE, t)
	);
	
	//attribute
	private final MutableProperty<IntPair> viewAreaSize =
	new MutableProperty<>(
		VIEW_AREA_SIZE_HEADER,
		vas -> setViewAreaSize(vas.getValue1(), vas.getValue2()),
		s -> new IntPair(s.getRefAttributeAt(1).toInt(), s.getRefAttributeAt(2).toInt()),
		vas -> Node.withAttribute(vas.getValue1(), vas.getValue2())
	);
	
	//attribute
	private final MutableProperty<IntPair> viewAreaCursorPosition =
	new MutableProperty<>(
		VIEW_AREA_CURSOR_POSITION_HEADER,
		vas -> setViewAreaCursorPosition(vas.getValue1(), vas.getValue2()),
		s -> new IntPair(s.getRefAttributeAt(1).toInt(), s.getRefAttributeAt(2).toInt()),
		vas -> Node.withAttribute(vas.getValue1(), vas.getValue2())
	);
	
	//attributes
	private final CloseController closeController = new CloseController(this);
	private final CachingContainer<Image> imageCache = new CachingContainer<>();
	private final KeyBoard keyBoard = new KeyBoard();
	private IFrontEndReader frontEndReader = new LocalFrontEndReader();
	private IFrontEndWriter frontEndWriter = new LocalFrontEndWriter();
	
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
		
		setPreCloseAction(this::preClose);
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
		
		setPreCloseAction(this::preClose);
	}
	
	//method
	/**
	 * @return the {@link IFrontEndReader} of the current {@link GUI}.
	 */
	public final IFrontEndReader fromFrontEnd() {
		return frontEndReader;
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
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	/**
	 * @return the image cache of the current {@link GUI}.
	 */
	public final CachingContainer<Image> getRefImageCache() {
		return imageCache;
	}
	
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
	public final int getViewAreaCursorXPosition() {
		return viewAreaCursorPosition.getValue().getValue1();
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the view area of the current {@link GUI}.
	 */
	public final int getViewAreaCursorYPosition() {
		return viewAreaCursorPosition.getValue().getValue2();
	}
	
	//method
	/**
	 * @return the height of the view area of the current {@link GUI}.
	 */
	public final int getViewAreaHeight() {
		return viewAreaSize.getValue().getValue2();
	}
	
	//method
	/**
	 * @return the width of the view area of the current {@link GUI}.
	 */
	public final int getViewAreaWidth() {
		return viewAreaSize.getValue().getValue1();
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
	public final boolean isVisible() {
		return (visualizer != null);
	}
	
	//method
	/**
	 * @param key
	 * @return true if the given key is pressed on the current {@link GUI}.
	 */
	public final boolean keyIsPressed(final Key key) {
		return keyBoard.keyIsPressed(key);
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note a resize.
	 * The size of the view area of the current {@link GUI} will be set to the size of the view area of the given pGUI.
	 * 
	 * @param pGUI
	 */
	public final void noteResizeFrom(final GUI<?> pGUI) {
		noteResize(pGUI.getViewAreaWidth(), pGUI.getViewAreaHeight());
	}
	
	//method
	/**
	 * @return the {@link IFrontEndWriter} of the current {@link GUI}.
	 */
	public final IFrontEndWriter onFrontEnd() {
		return frontEndWriter;
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
		setViewAreaSize(0, 0);
		setViewAreaCursorPosition(0, 0);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the front-end reader and the front-end writer of the current {@link GUI}.
	 * 
	 * @param frontEndReader
	 * @param frontEndWriter
	 * @throws ArgumentIsNullException if the given frontEndReader is null.
	 * @throws ArgumentIsNullException if the given frontEndWriter is null.
	 */
	public final void setFrontEndReaderAndFrontEndWriter(
		final IFrontEndReader frontEndReader,
		final IFrontEndWriter frontEndWriter
	) {
		
		Validator.assertThat(frontEndReader).thatIsNamed(IFrontEndReader.class).isNotNull();
		Validator.assertThat(frontEndWriter).thatIsNamed(IFrontEndWriter.class).isNotNull();
		
		this.frontEndReader = frontEndReader;
		this.frontEndWriter = frontEndWriter;
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
	protected void setViewAreaSize(final int viewAreaWidth, final int viewAreaHeight) {
		
		Validator.assertThat(viewAreaWidth).thatIsNamed("view area width").isNotNegative();
		Validator.assertThat(viewAreaHeight).thatIsNamed("view area height").isNotNegative();
		
		this.viewAreaSize.setValue(new IntPair(viewAreaWidth, viewAreaHeight));
	}
	
	//method
	protected void setViewAreaCursorPosition(final int viewAreaCursorXPosition, final int viewAreaCursorYPosition) {
		viewAreaCursorPosition.setValue(new IntPair(viewAreaCursorXPosition, viewAreaCursorYPosition));
	}
	
	//method
	private void preClose() {
		if (isVisible()) {
			visualizer.noteClose();
		}
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
