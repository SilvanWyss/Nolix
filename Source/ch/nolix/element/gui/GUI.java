//package declaration
package ch.nolix.element.gui;

//own imports
import ch.nolix.common.caching.CachingContainer;
import ch.nolix.common.closeableelement.CloseController;
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.pair.IntPair;
import ch.nolix.common.processproperty.ChangeState;
import ch.nolix.common.skillapi.Recalculable;
import ch.nolix.common.state.Visibility;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.baseguiapi.IBaseGUI;
import ch.nolix.element.baseguiapi.IFrontEndReader;
import ch.nolix.element.baseguiapi.IFrontEndWriter;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.framevisualizer.FrameVisualizer;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.input.IResizableInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.inputdevice.KeyBoard;
import ch.nolix.element.inputdeviceapi.IKeyBoard;
import ch.nolix.element.inputdeviceapi.IMutableKeyBoard;
import ch.nolix.element.painterapi.IPainter;

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
 * @lines 740
 * @param <G> is the type of a {@link GUI}.
 */
public abstract class GUI<G extends GUI<G>> extends ConfigurationElement<G> implements IBaseGUI<G>, Recalculable {
	
	//constants
	public static final String DEFAULT_TITLE = "GUI";
	public static final IntPair DEFAULT_VIEW_AREA_SIZE = new IntPair(100, 100);
	public static final IntPair DEFAULT_CURSOR_POSITION_ON_VIEW_AREA = new IntPair(-1, -1);
	
	//constants
	private static final String VIEW_AREA_SIZE_HEADER = "ViewAreaSize";
	private static final String CURSOR_POSITION_ON_VIEW_AREA_HEADER = "CursorPositionOnViewArea";
	
	//attribute
	private final MutableValue<String> title =
	new MutableValue<>(
		PascalCaseNameCatalogue.TITLE,
		DEFAULT_TITLE,
		this::setTitle,
		BaseNode::getOneAttributeHeader,
		Node::withAttribute
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
	private final CachingContainer<Image> imageCache = new CachingContainer<>();
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
				
		Validator.assertThat(visualizer).thatIsNamed(VariableNameCatalogue.VISUALIZER).isNotNull();
		Validator.assertThat(inputTaker).thatIsNamed("input taker").isNotNull();
		
		this.visualizer = visualizer;
		this.inputTaker = inputTaker;
		setPreCloseAction(this::preClose);
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
		setPreCloseAction(this::preClose);
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
		
		Validator.assertThat(visualizer).thatIsNamed(VariableNameCatalogue.VISUALIZER).isNotNull();
		
		this.inputTaker = null;
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
		
		this.inputTaker = null;
		
		if (visibility == Visibility.INVISIBLE) {
			visualizer = null;
		} else {
			visualizer = new FrameVisualizer();
		}
		
		setPreCloseAction(this::preClose);
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
	public final void noteKeyPress(final Key key) {
		if (inputTaker != null) {
			inputTaker.noteKeyPress(key);
		} else {
			noteKeyPressWhenDoesNotHaveInputTaker(key);
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
	public final void setPreCloseAction(final IAction preCloseAction) {
		
		//This implementation just ensures that it cannot be overwritten.
		IBaseGUI.super.setPreCloseAction(preCloseAction);
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
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpConfigurationElementAttributesInto(final LinkedList<Node> list) {
		fillUpGUIAttributesInto(list);
	}
	
	//method declaration
	/**
	 * Fills up the attributes of the current {@link GUI} into the given list.
	 * 
	 * @param list
	 */
	protected abstract void fillUpGUIAttributesInto(LinkedList<Node> list);
	
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
	
	//method declaration
	protected abstract void noteKeyPressWhenDoesNotHaveInputTaker(Key key);
	
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
	 * Lets the current {@link GUI} do a pre-close.
	 */
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
