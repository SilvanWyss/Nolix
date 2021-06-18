//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.caching.CachingContainer;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.pair.IntPair;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.common.programcontrol.processproperty.ChangeState;
import ch.nolix.common.skillapi.Recalculable;
import ch.nolix.common.state.Visibility;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.baseapi.IBaseGUI;
import ch.nolix.element.gui.baseapi.IFrontEndReader;
import ch.nolix.element.gui.baseapi.IFrontEndWriter;
import ch.nolix.element.gui.framevisualizer.FrameVisualizer;
import ch.nolix.element.gui.image.Image;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.input.IResizableInputTaker;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.inputdevice.KeyBoard;
import ch.nolix.element.gui.inputdeviceapi.IKeyBoard;
import ch.nolix.element.gui.inputdeviceapi.IMutableKeyBoard;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * A {@link GUI} is mainly a {@link IBaseGUI} and a {@link ConfigurationElement}.
 * A {@link GUI} contains so-called hard attributes and so-called soft attribues.
 * A hard attribute is an attribute that is not changed implicitly by a user interaction. E.g. title, background color.
 * A soft attribute is an attribute that can be changed implicitly by a user interaction. E.g. cursor position.
 * 
 * The {@link GUI#getAttributes} method of a {@link GUI} must deliver all attributes; the hard and the soft ones.
 * The reason is because when a {@link GUI} is used in a server-client application,
 * the soft attributes need to be transferred as well.
 * When the soft attributes would be refreshed on client-side only, e.g. by a mouse-move,
 * and a new version of the {@link GUI} is requested from the server,
 * the server would deliver a {@link GUI} with its out-dated soft attributes.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 760
 * @param <G> is the type of a {@link GUI}.
 */
public abstract class GUI<G extends GUI<G>> extends ConfigurationElement<G> implements IBaseGUI<G>, Recalculable {
	
	//constants
	public static final String DEFAULT_TITLE = PascalCaseCatalogue.GUI;
	public static final Image DEFAULT_ICON = GUIIconCatalogue.NOLIX_ICON;
	public static final IntPair DEFAULT_VIEW_AREA_SIZE = new IntPair(100, 100);
	public static final IntPair DEFAULT_CURSOR_POSITION_ON_VIEW_AREA = new IntPair(-1, -1);
	
	//constants
	private static final String TITLE_HEADER = PascalCaseCatalogue.TITLE;
	private static final String ICON_HEADER = PascalCaseCatalogue.ICON;
	private static final String VIEW_AREA_SIZE_HEADER = "ViewAreaSize";
	private static final String CURSOR_POSITION_ON_VIEW_AREA_HEADER = "CursorPositionOnViewArea";
	
	//attribute
	private final MutableValue<String> title =
	new MutableValue<>(
		TITLE_HEADER,
		DEFAULT_TITLE,
		this::setTitle,
		BaseNode::getOneAttributeHeader,
		Node::withAttribute
	);
	
	//attribute
	private final MutableValue<Image> icon =
	new MutableValue<>(
		ICON_HEADER,
		DEFAULT_ICON,
		this::setIcon,
		Image::fromSpecification,
		Image::getSpecification
	);
	
	//attribute
	private final MutableValue<IntPair> viewAreaSize =
	new MutableValue<>(
		VIEW_AREA_SIZE_HEADER,
		DEFAULT_VIEW_AREA_SIZE,
		vas -> setViewAreaSize(vas.getValue1(), vas.getValue2()),
		BaseNode::toIntPair,
		Node::fromIntPair
	);
	
	//attribute
	private final MutableValue<IntPair> cursorPositionOnViewArea =
	new MutableValue<>(
		CURSOR_POSITION_ON_VIEW_AREA_HEADER,
		DEFAULT_CURSOR_POSITION_ON_VIEW_AREA,
		cpova -> setCursorPositionOnViewArea(cpova.getValue1(), cpova.getValue2()),
		BaseNode::toIntPair,
		Node::fromIntPair
	);
	
	//attributes
	private final CloseController closeController = new CloseController(this);
	private IFrontEndReader frontEndReader = new LocalFrontEndReader();
	private IFrontEndWriter frontEndWriter = new LocalFrontEndWriter();
	private final CachingContainer<MutableImage> imageCache = new CachingContainer<>();
	private final KeyBoard keyBoard = new KeyBoard();
	private boolean viewAreaSizeHasChangedSinceLastRecalculation = true;
	
	//optional attribute
	private final IVisualizer visualizer;
	private final IResizableInputTaker inputTaker;
	
	//constructor
	/**
	 * Creates a new {@link GUI}.
	 * The {@link GUI} will be visible and have the given visualizer.
	 * The {@link GUI} will have the given inputTaker.
	 * 
	 * @param visualizer
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 */
	public GUI(final IVisualizer visualizer, final IResizableInputTaker inputTaker) {
				
		Validator.assertThat(visualizer).thatIsNamed(LowerCaseCatalogue.VISUALIZER).isNotNull();
		Validator.assertThat(inputTaker).thatIsNamed("input taker").isNotNull();
		
		this.visualizer = visualizer;
		this.inputTaker = inputTaker;
	}
	
	//constructor
	/**
	 * Creates a new {@link GUI}.
	 * The {@link GUI} will be visible according to the given visibility.
	 * The {@link GUI} will have the given inputTaker.
	 * 
	 * @param visibility
	 * @param inputTaker
	 */
	public GUI(final Visibility visibility, final IResizableInputTaker inputTaker) {
		
		Validator.assertThat(visibility).thatIsNamed(Visibility.class).isNotNull();
		Validator.assertThat(inputTaker).thatIsNamed("input taker").isNotNull();
		
		if (visibility == Visibility.INVISIBLE) {
			visualizer = null;
		} else {
			visualizer = new FrameVisualizer();
		}
		
		this.inputTaker = inputTaker;
	}
	
	//constructor
	/**
	 * Creates a new {@link GUI}.
	 * The {@link GUI} will be visible and have the given visualizer.
	 * 
	 * @param visualizer
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 */
	public GUI(final IVisualizer visualizer) {
		
		Validator.assertThat(visualizer).thatIsNamed(LowerCaseCatalogue.VISUALIZER).isNotNull();
		
		this.inputTaker = null;
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
		
		this.inputTaker = null;
		
		if (visibility == Visibility.INVISIBLE) {
			visualizer = null;
		} else {
			visualizer = new FrameVisualizer();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IFrontEndReader fromFrontEnd() {
		return frontEndReader;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCursorXPositionOnViewArea() {
		return cursorPositionOnViewArea.getValue().getValue1();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCursorYPositionOnViewArea() {
		return cursorPositionOnViewArea.getValue().getValue2();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Image getIcon() {
		return icon.getValue();
	}
	
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
	public final CachingContainer<MutableImage> getRefImageCache() {
		return imageCache;
	}
	
	//method
	/**
	 * @return the {@link IKeyBoard} of the current {@link GUI}.
	 */
	public final IKeyBoard getRefKeyBoard() {
		return keyBoard;
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
	 * @return the height of the view area of the current {@link GUI}.
	 */
	public final int getViewAreaHeight() {
		return viewAreaSize.getValue().getValue2();
	}
	
	//method
	/**
	 * @return the view area size of the current {@link GUI}.
	 */
	public final IntPair getViewAreaSize() {
		return viewAreaSize.getValue();
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
	 * {@inheritDoc}
	 */
	@Override
	public void noteClose() {
		if (isVisible()) {
			visualizer.noteClose();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyDown(final Key key) {
		if (inputTaker != null) {
			inputTaker.noteKeyDown(key);
		} else {
			noteKeyDownWhenDoesNotHaveInputTaker(key);
		}
	}
	
	

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyRelease(final Key key) {		
		if (inputTaker != null) {
			inputTaker.noteKeyRelease(key);
		} else {
			noteKeyReleaseWhenDoesNotHaveInputTaker(key);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyTyping(final Key key) {		
		if (inputTaker != null) {
			inputTaker.noteKeyTyping(key);
		} else {
			noteKeyTypingWhenDoesNotHaveInputTaker(key);
		}
	}


	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonClick() {		
		if (inputTaker != null) {
			inputTaker.noteLeftMouseButtonClick();
		} else {
			noteLeftMouseButtonClickWhenDoesNotHaveInputTaker();
		}
	}
	
	

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonPress() {
		if (inputTaker != null) {
			inputTaker.noteLeftMouseButtonPress();
		} else {
			noteLeftMouseButtonPressWhenDoesNotHaveInputTaker();
		}
	}


	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonRelease() {
		if (inputTaker != null) {
			inputTaker.noteLeftMouseButtonRelease();
		} else {
			noteLeftMouseButtonReleaseWhenDoesNotHaveInputTaker();
		}
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {
		
		setCursorPositionOnViewArea(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		
		if (inputTaker != null) {
			inputTaker.noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		} else {
			noteMouseMoveWhenDoesNotHaveInputTaker(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelClick() {
		if (inputTaker != null) {
			inputTaker.noteMouseWheelClick();
		} else {
			noteMouseWheelClickWhenDoesNotHaveInputTaker();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelPress() {
		if (inputTaker != null) {
			inputTaker.noteMouseWheelPress();
		} else {
			noteMouseWheelPressWhenDoesNotHaveInputTaker();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRelease() {
		if (inputTaker != null) {
			inputTaker.noteMouseWheelRelease();
		} else {
			noteMouseWheelReleaseWhenDoesNotHaveInputTaker();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {
		if (inputTaker != null) {
			inputTaker.noteMouseWheelRotationStep(rotationDirection);
		} else {
			noteMouseWheelRotationStepWhenDoesNotHaveInputTaker(rotationDirection);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		
		setViewAreaSize(viewAreaWidth, viewAreaHeight);
		
		if (inputTaker != null) {
			inputTaker.noteResize(viewAreaWidth, viewAreaHeight);
		} else {
			noteResizeWhenDoesNotHaveInputTaker(viewAreaWidth, viewAreaHeight);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		if (inputTaker != null) {
			inputTaker.noteRightMouseButtonClick();
		} else {
			noteRightMouseButtonClickWhenDoesNotHaveInputTaker();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonPress() {
		if (inputTaker != null) {
			inputTaker.noteRightMouseButtonPress();
		} else {
			noteRightMouseButtonPressWhenDoesNotHaveInputTaker();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonRelease() {
		if (inputTaker != null) {
			inputTaker.noteRightMouseButtonRelease();
		} else {
			noteRightMouseButtonReleaseWhenDoesNotHaveInputTaker();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
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
	public final void recalculate() {
		if (!viewAreaSizeHasChangedSinceLastRecalculation) {
			recalculate(ChangeState.UNCHANGED);
		} else {
			recalculate(ChangeState.CHANGED);
			viewAreaSizeHasChangedSinceLastRecalculation = false;
		}
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
	 * {@inheritDoc}
	 */
	@Override
	public final G setIcon(final Image icon) {
		
		this.icon.setValue(icon.toStretchedImage(64, 64).toImmutableImage());
		
		return asConcrete();
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
	@Override
	public final G setTitle(final String title) {
		
		Validator.assertThat(title).thatIsNamed(LowerCaseCatalogue.TITLE).isNotBlank();
		
		this.title.setValue(title);
		
		return asConcrete();
	}
	
	//method
	/**
	 * @return true if the view area of the current {@link GUI} is under the cursor.
	 */
	public final boolean viewAreaIsUnderCursor() {
		
		final var cursorXPositionOnViewArea = getCursorXPositionOnViewArea();
		final var cursorYPositionOnViewArea = getCursorYPositionOnViewArea();
		
		return
		cursorXPositionOnViewArea >= 0
		&& cursorYPositionOnViewArea >= 0
		&& cursorXPositionOnViewArea < getViewAreaWidth()
		&& cursorYPositionOnViewArea < getViewAreaHeight();
	}
	
	//method
	/**
	 * @return the {@link IMutableKeyBoard} of the current {@link GUI} .
	 */
	protected final IMutableKeyBoard getRefKeyBoardForMutating() {
		return keyBoard;
	}
	
	//method
	/**
	 * @return the {@link IVisualizer} of the current {@link GUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link GUI} does not have a {@link IVisualizer}.
	 */
	protected final IVisualizer getRefVisualizer() {
		
		if (visualizer == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VISUALIZER);
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
	
	//method declaration
	protected abstract void noteKeyDownWhenDoesNotHaveInputTaker(Key key);
	
	//method declaration
	protected abstract void noteKeyReleaseWhenDoesNotHaveInputTaker(Key key);
	
	//method declaration
	protected abstract void noteKeyTypingWhenDoesNotHaveInputTaker(Key key);
	
	//method declaration
	protected abstract void noteLeftMouseButtonClickWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteLeftMouseButtonPressWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteLeftMouseButtonReleaseWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteMouseMoveWhenDoesNotHaveInputTaker(
		int cursorXPositionOnViewArea,
		int cursorYPositionOnViewArea
	);
	
	//method declaration
	protected abstract void noteMouseWheelClickWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteMouseWheelPressWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteMouseWheelReleaseWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteMouseWheelRotationStepWhenDoesNotHaveInputTaker(
		RotationDirection rotationDirection
	);
	
	//method declaration
	protected abstract void noteResizeWhenDoesNotHaveInputTaker(int viewAreaWidth, int viewAreaHeight);
	
	//method declaration
	protected abstract void noteRightMouseButtonClickWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteRightMouseButtonPressWhenDoesNotHaveInputTaker();
	
	//method declaration
	protected abstract void noteRightMouseButtonReleaseWhenDoesNotHaveInputTaker();
	
	//method declaration	
	protected abstract void recalculate(ChangeState viewAreaChangeState);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetConfigurationElement() {
		
		setTitle(DEFAULT_TITLE);
		setViewAreaSize(0, 0);
		setCursorPositionOnViewArea(0, 0);
		
		resetGUI();
	}
	
	//method declaration
	/**
	 * Resets the current {@link GUI}.
	 */
	protected abstract void resetGUI();
	
	//method
	private void setCursorPositionOnViewArea(final int viewAreaCursorXPosition, final int viewAreaCursorYPosition) {
		cursorPositionOnViewArea.setValue(new IntPair(viewAreaCursorXPosition, viewAreaCursorYPosition));
	}
	
	//method
	private void setViewAreaSize(final int viewAreaWidth, final int viewAreaHeight) {
		
		Validator.assertThat(viewAreaWidth).thatIsNamed("view area width").isNotNegative();
		Validator.assertThat(viewAreaHeight).thatIsNamed("view area height").isNotNegative();
		
		this.viewAreaSize.setValue(new IntPair(viewAreaWidth, viewAreaHeight));
		viewAreaSizeHasChangedSinceLastRecalculation = true;
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
