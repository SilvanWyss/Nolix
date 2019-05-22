//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.elementEnums.ContentPosition;
import ch.nolix.core.elementEnums.ExtendedContentPosition;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.discreteGeometry.Discrete2DPoint;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widget.CursorIcon;
import ch.nolix.element.widget.IGUI;
import ch.nolix.element.widget.IGUILayer;
import ch.nolix.element.widget.Widget;

//class
/**
 * A {@link GUILayer} has:
 * -an optional background {@link Color} or background {@ColorGradient}
 * -an optional root {@link Widget}
 * 
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 550
 */
public final class GUILayer extends MutableElement<GUILayer> implements IGUILayer<GUILayer> {
	
	//default values
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final ExtendedContentPosition DEFAULT_CONTENT_POSITION = ExtendedContentPosition.Top;
	public static final Discrete2DPoint DEFAULT_FREE_CONTENT_POSITION = new Discrete2DPoint(1, 1);
	
	//constants
	static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	static final String ROOT_WIDGET_HEADER = "RootWidget";
	static final String FREE_CONTENT_POSITION_HEADER = "FreeContentPosition";
	
	//static method
	/**
	 * Creates a new {@link GUILayer} from the given specification.
	 * 
	 * @param specification
	 * @return a new {@link GUILayer} from the given specification that will belong to the given parentGUI.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static GUILayer createFromSpecification(final DocumentNodeoid specification) {
		return new GUILayer().reset(specification);
	}
	
	//attribute
	/**
	 * The {@link GUI} the current {@link GUILayer} belongs to.
	 */
	private IGUI<?> parentGUI;
	
	//attribute
	private final MutableOptionalProperty<Color> backgroundColor =
	new MutableOptionalProperty<Color>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		bc -> setBackgroundColor(bc),
		s -> Color.createFromSpecification(s),
		bc -> bc.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<ColorGradient> backgroundColorGradient =
	new MutableOptionalProperty<ColorGradient>(
		BACKGROUND_COLOR_GRADIENT_HEADER, 
		bcg -> setBackgroundColorGradient(bcg),
		s -> ColorGradient.createFromSpecification(s),
		bcg -> bcg.getSpecification()
	);
	
	//attribute
	private final MutableProperty<ExtendedContentPosition> contentPosition =
	new MutableProperty<ExtendedContentPosition>(
		ContentPosition.TYPE_NAME,
		cp -> setContentPosition(cp),
		s -> ExtendedContentPosition.createFromSpecification(s),
		cp -> cp.getSpecification()
	);
	
	//attribute
	private final MutableProperty<Discrete2DPoint> freeContentPosition =
	new MutableProperty<Discrete2DPoint>(
		FREE_CONTENT_POSITION_HEADER,
		fcp -> setFreeContentPosition(fcp.getX(), fcp.getY()),
		s -> Discrete2DPoint.createFromSpecification(s),
		fcp -> fcp.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<Widget<?, ?>> rootWidget =
	new MutableOptionalProperty<Widget<?, ?>>(
		ROOT_WIDGET_HEADER,
		rw -> setRootWidget(rw),
		s -> GUI.createWidget(s),
		s -> s.getSpecification()
	);
	
	//constructor
	/**
	 * Creates a new {@link GUILayer}.
	 * 
	 * @param parentGUI
	 */
	public GUILayer() {
		reset();
	}
	
	//constructor
	/**
	 * Creates a new {@link GUILayer} with the given contentPosition and rootWidget.
	 * 
	 * @param contentPosition
	 * @param rootWidget
	 * @throws NullArgumentException if the given contentPosition is null.
	 * @throws NullArgumentException if the given rootWidget is null.
	 */
	public GUILayer(ExtendedContentPosition contentPosition, Widget<?, ?> rootWidget) {
		
		//Calls other constructor.
		this(rootWidget);
		
		setContentPosition(contentPosition);
	}
	
	//constructor
	/**
	 * Creates a new {@link GUILayer} with the given rootWidget.
	 * 
	 * @param rootWidget
	 * @throws NullArgumentException if the given parentGUI is null.
	 * @throws NullArgumentException if the given rootWidget is null.
	 */
	public GUILayer(final Widget<?, ?> rootWidget) {
		
		//Calls other constructor.
		this();
		
		setRootWidget(rootWidget);
	}
	
	//method
	/**
	 * Removes the root {@link Widget} of the current {@link GUILayer}.
	 * 
	 * @return the current {@link GUILayer}.
	 */
	@Override
	public GUILayer clear() {
		
		rootWidget.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return the background {@link Color} of the current {@link GUILayer}.
	 */
	public Color getBackgroundColor() {
		return backgroundColor.getValue();
	}
	
	//method
	/**
	 * @return the background {@link ColorGradient} of the current {@link GUILayer}.
	 */
	public ColorGradient getBackgroundColorGradient() {
		return backgroundColorGradient.getValue();
	}
	
	//method
	/**
	 * @return the x-free-position of the current {@link GUILayer}.
	 */
	public int getContentXFreePosition() {
		return freeContentPosition.getValue().getX();
	}
	
	//method
	/**
	 * @return the y-free-position of the current {@link GUILayer}.
	 */
	public int getContentYFreePosition() {
		return freeContentPosition.getValue().getY();
	}
	
	//method
	/**
	 * @return the {@link CursorIcon} of the current {@link GUILayer}.
	 */
	public CursorIcon getCursorIcon() {
		
		if (rootWidget.containsAny() && rootWidget.getValue().isUnderCursor()) {
			return rootWidget.getValue().getCursorIcon();
		}
				
		return CursorIcon.Arrow;
 	}
	
	//method
	/**
	 * @return the root {@link Widget} of the current {@link GUILayer}.
	 */
	public Widget<?, ?> getRefRootWidget() {
		return rootWidget.getValue();
	}
	
	//method
	/**
	 * @return the {@link Widget}s of the current {@link GUILayer} recursively.
	 */
	public List<Widget<?, ?>> getRefWidgetsRecursively() {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Handles the case that the current GUILayer does not have a root Widget.
			if (rootWidget.isEmpty()) {
				return new List<Widget<?, ?>>();
			}
			
			//Handles the case that the current GUILayer has a root Widget.			
				//Extracts the root Widget of the current GUILayer.
				final var rootWidget = this.rootWidget.getValue();
				
				return rootWidget.getChildWidgetsRecursively().addAtEnd(rootWidget);
	}
	
	//method
	/**
	 * @return the triggerable {@link Widget}s of the current {@link GUILayer} recursively.
	 */
	public List<Widget<?, ?>> getRefTriggerableWidgetsRecursively() {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Handles the case that the current GUILayer does not have a root Widget.
			if (rootWidget.isEmpty()) {
				return new List<Widget<?, ?>>();
			}
			
			//Handles the case that the current GUILayer has a root Widget.			
				//Extracts the root Widget of the current GUILayer.
				final var rootWidget = this.rootWidget.getValue();
				
				return rootWidget.getTriggerableChildWidgetsRecursively().addAtEnd(rootWidget);
	}
	
	//method
	/**
	 * @return the background {@link Color} of the current {@link GUILayer}.
	 */
	public boolean hasBackgroundColor() {
		return backgroundColor.containsAny();
	}
	
	//method
	/**
	 * @return the background {@link ColorGradient} of the current {@link GUILayer}.
	 */
	public final boolean hasBackgroundColorGradient() {
		return backgroundColorGradient.containsAny();
	}
	
	//method
	/**
	 * @return true if the current {@link GUILayer} has a root {@link Widget}.
	 */
	@Override
	public boolean isEmpty() {
		return rootWidget.isEmpty();
	}
	
	//method
	/**
	 * Removes any background of the current {@link GUILayer}.
	 * 
	 * @return the current {@link GUILayer}.
	 */
	public GUILayer removeBackground() {
		
		backgroundColor.clear();
		backgroundColorGradient.clear();
		
		return this;
	}
	
	//method
	/**
	 * Resets the current {@link GUILayer}.
	 * 
	 * @return the current {@link GUILayer}.
	 */
	@Override
	public GUILayer reset() {
		
		setFreeContentPosition(DEFAULT_FREE_CONTENT_POSITION);
		clear();
		resetConfiguration();
		
		return this;
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link GUILayer}.
	 * 
	 * @return the current {@link GUILayer}.
	 */
	public void resetConfiguration() {
		
		removeBackground();
		setContentPosition(DEFAULT_CONTENT_POSITION);
		
		//Handles the case that the current GUILayer has a root Widget.
		//For a better performance, this implementation does not use all comfortable methods.
		if (rootWidget.containsAny()) {
			rootWidget.getValue().resetConfiguration();
		}
	}
	
	//method
	/**
	 * Sets the background {@link Color} of the current {@link GUILayer}.
	 * Removes any former background of the current {@link GUILayer}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link GUILayer}.
	 * @throws NullArgumentException if the given backgroundColor is null.
	 */
	public GUILayer setBackgroundColor(final Color backgroundColor) {
		
		removeBackground();
		
		this.backgroundColor.setValue(backgroundColor);
		
		return this;
	}
	
	//method
	/**
	 * Sets the background {@link ColorGradient} of the current {@link GUILayer}.
	 * Removes any former background of the current {@link GUILayer}.
	 * 
	 * @param backgroundColorGradient
	 * @return the current {@link GUILayer}.
	 * @throws NullArgumentException if the given backgroundColorGradient is null.
	 */
	public GUILayer setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		removeBackground();
		
		this.backgroundColorGradient.setValue(backgroundColorGradient);
		
		return this;
	}
	
	//method
	/**
	 * Sets the {@link ExtendedContentPosition} of the current {@link GUILayer}.
	 * 
	 * @param contentPosition
	 * @return the current {@link GUILayer}.
	 * @throws NullArgumentException if the given contentPosition is null.
	 */
	public GUILayer setContentPosition(final ExtendedContentPosition contentPosition) {
		
		this.contentPosition.setValue(contentPosition);
		
		return this;
	}
	
	/**
	 * Sets the free content position of the current {@link GUILayer}.
	 * 
	 * @param freeContentPosition
	 * @return the current {@link GUILayer}.
	 * @throws NullArgumentException if the given freeContentPosition is null.
	 */
	public GUILayer setFreeContentPosition(final Discrete2DPoint freeContentPosition) {
		
		this.freeContentPosition.setValue(freeContentPosition);
		
		return this;
	}
	
	/**
	 * Sets the free content position of the current {@link GUILayer}.
	 * 
	 * @param x
	 * @param y
	 * @return the current {@link GUILayer}.
	 */
	public GUILayer setFreeContentPosition(final int x, final int y) {
		
		//Calls other method.
		return setFreeContentPosition(new Discrete2DPoint(x, y));
	}
	
	//method
	/**
	 * Sets the root {@link Widget} of the current {@link GUILayer}.
	 * 
	 * @param rootWidget
	 * @return the current {@link GUILayer}.
	 * @throws NullArgumentException if the given rootWidget is null.
	 */
	public GUILayer setRootWidget(final Widget<?, ?> rootWidget) {
				
		this.rootWidget.setValue(rootWidget);
		
		if (parentGUI != null) {
			rootWidget.setParentGUI(parentGUI);
		}
		
		return this;
	}
	
	//package-visible method
	/**
	 * Paints the current  {@link GUILayer} using the given painter.
	 * 
	 * @param painter
	 */
	public void paint(final IPainter painter) {
		
		//Paints the background of the current GUILayer.
			//Handles the case that the current GUILayer has a background color.
			if (hasBackgroundColor()) {
				painter.setColor(getBackgroundColor());
				painter.paintFilledRectangle(parentGUI.getViewAreaWidth(), parentGUI.getViewAreaHeight());
			}
			
			//Handles the case that the current GUILayer has a background color gradient.
			else if (hasBackgroundColorGradient()) {
				painter.setColorGradient(getBackgroundColorGradient());
				painter.paintFilledRectangle(parentGUI.getViewAreaWidth(), parentGUI.getViewAreaHeight());
		}
		
		//Handles the case that the current GUILayer has a root Widget.
		//For a better performance, this implementation does not use all comfortable methods.
		if (rootWidget.containsAny()) {
			rootWidget.getValue().paint(painter);
		}
	}
	
	//package-visible method
	/**
	 * Recalculates the current  {@link GUILayer}.
	 */
	public void recalculate() {
		
		//Handles the case that the current GUILayer has a root Widget.
		//For a better performance, this implementation does not use all comfortable methods.
		if (rootWidget.containsAny()) {
			
			//Extracts the root Widget of the current GUILayer.
			final var rootWidget = this.rootWidget.getValue();
						
			//Enumerates the content position of the current GUILayer.
			switch (contentPosition.getValue()) {
				case LeftTop:
					
					getRefRootWidget().setPositionOnParent(0, 0);
					
					break;
				case Left:
					
					getRefRootWidget().setPositionOnParent(
						0,
						Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;
				case LeftBottom:
					
					getRefRootWidget().setPositionOnParent(
						0,
						Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case Top:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
						0
					);
					
					break;
				case Center:
								
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;
				case Bottom:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case RightTop:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
						0
					);
					
					break;
				case Right:
				
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
					);
				
					break;
				case RightBottom:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case Free:
					
					final var freeContentPosition = this.freeContentPosition.getValue();					
					getRefRootWidget().setPositionOnParent(freeContentPosition.getX(), freeContentPosition.getY());
					
					break;
			}
			
			rootWidget.recalculateRecursively();
		}
	}
	
	//package-visible method
	public GUILayer setParentGUI(final IGUI<?> parentGUI) {
		
		Validator.suppose(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		if (this.parentGUI != null && parentGUI != this.parentGUI) {
			throw new InvalidArgumentException(this, "belongs already to another parent GUI");
		}
		
		this.parentGUI = parentGUI;
		
		if (rootWidget.hasValue()) {
			rootWidget.getValue().setParentGUI(parentGUI);
		}
		
		return this;
	}
}
