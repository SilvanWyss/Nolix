//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateelement.ForwardingProperty;
import ch.nolix.system.element.multistateelement.NonCascadingProperty;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.image.Background;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
public abstract class BorderWidgetLook<BWL extends BorderWidgetLook<BWL>> extends WidgetLook<BWL> {
	
	//constants
	public static final int DEFAULT_BORDER_THICKNESS = 0;
	public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	public static final int DEFAULT_PADDING = 0;
	public static final Color DEFAULT_SCROLL_CURSOR_COLOR = Color.DARK_GREY;
	public static final Color DEFAULT_SCROLL_CURSOR_HOVER_COLOR = Color.GREY;
	public static final Color DEFAULT_SCROLL_CURSOR_MOVE_COLOR = Color.DIM_GREY;
	
	//constants
	private static final String LEFT_BORDER_THICKNESS_HEADER = "LeftBorderThickness";
	private static final String RIGHT_BORDER_THICKNESS_HEADER = "RightBorderThickness";
	private static final String TOP_BORDER_THICKNESS_HEADER = "TopBorderThickness";
	private static final String BOTTOM_BORDER_THICKNESS_HEADER = "BottomBorderThickness";
	private static final String LEFT_BORDER_COLOR_HEADER = "LeftBorderColor";
	private static final String RIGHT_BORDER_COLOR_HEADER = "RightBorderColor";
	private static final String TOP_BORDER_COLOR_HEADER = "TopBorderColor";
	private static final String BOTTOM_BORDER_COLOR_HEADER = "BottomBorderColor";
	private static final String BACKGROUND_HEADER = "Background";
	private static final String LEFT_PADDING_HEADER = "LeftPadding";
	private static final String RIGHT_PADDING_HEADER = "RightPadding";
	private static final String TOP_PADDING_HEADER = "TopPadding";
	private static final String BOTTOM_PADDING_HEADER = "BottomPadding";
	private static final String SCROLL_BAR_COLOR_HEADER = "ScrollBarColor";
	private static final String SCROLL_BAR_HOVER_COLOR_HEADER = "ScrollBarHoverColor";
	private static final String SCROLL_BAR_MOVE_COLOR_HEADER = "ScrollBarMoveColor";
	private static final String SCROLL_CURSOR_COLOR_HEADER = "ScrollCursorColor";
	private static final String SCROLL_CURSOR_HOVER_COLOR_HEADER = "ScrollCursorHoverColor";
	private static final String SCROLL_CURSOR_MOVE_COLOR_HEADER = "ScrollCursorMoveColor";
	private static final String BORDER_COLOR_HEADER = "BorderColor";
	private static final String BORDER_THICKNESS_HEADER = "BorderThickness";
	private static final String PADDING_HEADER = "Padding";
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> leftBorderThickness =
	new NonCascadingProperty<>(
		LEFT_BORDER_THICKNESS_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setLeftBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> rightBorderThickness =
	new NonCascadingProperty<>(
		RIGHT_BORDER_THICKNESS_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setRightBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> topBorderThickness =
	new NonCascadingProperty<>(
		TOP_BORDER_THICKNESS_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setTopBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> bottomBorderThickness =
	new NonCascadingProperty<>(
		BOTTOM_BORDER_THICKNESS_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setBottomBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> leftBorderColor =
	new NonCascadingProperty<>(
		LEFT_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> rightBorderColor =
	new NonCascadingProperty<>(
		RIGHT_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> topBorderColor =
	new NonCascadingProperty<>(
		TOP_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> bottomBorderColor =
	new NonCascadingProperty<>(
		BOTTOM_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Background> background =
	new NonCascadingProperty<>(
		BACKGROUND_HEADER,
		ControlState.class,
		Background::fromSpecification,
		Background::getSpecification
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> leftPadding =
	new NonCascadingProperty<>(
		LEFT_PADDING_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setLeftPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> rightPadding =
	new NonCascadingProperty<>(
		RIGHT_PADDING_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setRightPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> topPadding =
	new NonCascadingProperty<>(
		TOP_PADDING_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setTopPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Integer> bottomPadding =
	new NonCascadingProperty<>(
		BOTTOM_PADDING_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setBottomPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> scrollBarColor =
	new NonCascadingProperty<>(
		SCROLL_BAR_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollBarColorForState
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> scrollBarHoverColor =
	new NonCascadingProperty<>(
		SCROLL_BAR_HOVER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollBarHoverColorForState
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> scrollBarMoveColor =
	new NonCascadingProperty<>(
		SCROLL_BAR_MOVE_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollBarMoveColorForState
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> scrollCursorColor =
	new NonCascadingProperty<>(
		SCROLL_CURSOR_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollCursorColorForState,
		DEFAULT_SCROLL_CURSOR_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> scrollCursorHoverColor =
	new NonCascadingProperty<>(
		SCROLL_CURSOR_HOVER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollCursorHoverColorForState,
		DEFAULT_SCROLL_CURSOR_HOVER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, Color> scrollCursorMoveColor =
	new NonCascadingProperty<>(
		SCROLL_CURSOR_MOVE_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollCursorMoveColorForState,
		DEFAULT_SCROLL_CURSOR_MOVE_COLOR
	);
	
	//attribute
	private final ForwardingProperty<ControlState, Integer> borderThickness =
	new ForwardingProperty<>(
		BORDER_THICKNESS_HEADER,
		leftBorderThickness,
		rightBorderThickness,
		topBorderThickness,
		bottomBorderThickness
	);
	
	//attribute
	private final ForwardingProperty<ControlState, Color> borderColor =
	new ForwardingProperty<>(
		BORDER_COLOR_HEADER,
		leftBorderColor,
		rightBorderColor,
		topBorderColor,
		bottomBorderColor
	);
	
	//attribute
	private final ForwardingProperty<ControlState, Integer> padding =
	new ForwardingProperty<>(PADDING_HEADER, leftPadding, rightPadding, topPadding, bottomPadding);
	
	//method
	public final IColor getBackgroundColor() {
		return background.getValue().getColor();
	}
	
	//method
	public final IColorGradient getBackgroundColorGradient() {
		return background.getValue().getColorGradient();
	}
	
	//method
	public final IImage getBackgroundImage() {
		return background.getValue().getImage();
	}
	
	//method
	public final ImageApplication getBackgroundImageApplication() {
		return background.getValue().getImageApplication();
	}
	
	//method
	public final Color getBottomBorderColor() {
		return bottomBorderColor.getValue();
	}
	
	//method
	public final int getBottomBorderThickness() {
		return bottomBorderThickness.getValue();
	}
	
	//method
	public final int getBottomPadding() {
		return bottomPadding.getValue();
	}
	
	//method
	public final Color getLeftBorderColor() {
		return leftBorderColor.getValue();
	}
	
	//method
	public final int getLeftBorderThickness() {
		return leftBorderThickness.getValue();
	}
	
	//method
	public final int getLeftPadding() {
		return leftPadding.getValue();
	}
	
	//method
	public final SingleContainer<Color> getOptionalScrollBarColor() {
		return scrollBarColor.getOptionalValue();
	}
	
	//method
	public final SingleContainer<Color> getOptionalScrollBarHoverColor() {
		return scrollBarHoverColor.getOptionalValue();
	}
	
	//method
	public final SingleContainer<Color> getOptionalScrollBarMoveColor() {
		return scrollBarMoveColor.getOptionalValue();
	}
	
	//method
	public final Color getRightBorderColor() {
		return rightBorderColor.getValue();
	}
	
	//method
	public final int getRightBorderThickness() {
		return rightBorderThickness.getValue();
	}
	
	//method
	public final int getRightPadding() {
		return rightPadding.getValue();
	}
	
	//method
	public final Color getScrollCursorColor() {
		return scrollCursorColor.getValue();
	}
	
	//method
	public final Color getScrollCursorHoverColor() {
		return scrollCursorHoverColor.getValue();
	}
	
	//method
	public final Color getScrollCursorMoveColor() {
		return scrollCursorMoveColor.getValue();
	}
	
	//method
	public final Color getTopBorderColor() {
		return topBorderColor.getValue();
	}
	
	//method
	public final int getTopBorderThickness() {
		return topBorderThickness.getValue();
	}
	
	//method
	public final int getTopPadding() {
		return topPadding.getValue();
	}
	
	//method
	public final boolean hasBackgroundColor() {
		return background.hasValue() && background.getValue().isColor();
	}
	
	//method
	public final boolean hasBackgroundColorGradient() {
		return background.hasValue() && background.getValue().isColorGradient();
	}
	
	//method
	public final boolean hasBackgroundImage() {
		return background.hasValue() && background.getValue().isImage();
	}
	
	//method
	public final boolean hasScrollBarColor() {
		return scrollBarColor.hasValue();
	}
	
	//method
	public final boolean hasScrollBarHoverColor() {
		return scrollBarHoverColor.hasValue();
	}
	
	//method
	public final boolean hasScrollBarMoveColor() {
		return scrollBarMoveColor.hasValue();
	}
	
	//method
	public final void removeBackground() {
		background.setUndefined();
	}
	
	//method
	public final void removeBorderThicknesses() {
		removeLeftBorderColors();
		removeRightBorderColors();
		removeTopBorderColors();
		removeBottomBorderColors();
	}
	
	//method
	public final void removeBottomBorderColors() {
		bottomBorderColor.setUndefined();
	}
	
	//method
	public final void removeBottomBorderThicknesses() {
		bottomBorderThickness.setUndefined();
	}
	
	//method
	public final void removeBottomPaddings() {
		bottomPadding.setUndefined();
	}
	
	//method
	public final void removeLeftBorderColors() {
		leftBorderColor.setUndefined();
	}
	
	//method
	public final void removeLeftBorderThicknesses() {
		leftBorderThickness.setUndefined();
	}
	
	//method
	public final void removeLeftPaddings() {
		leftPadding.setUndefined();
	}
	
	//method
	public final void removePaddings() {
		removeLeftPaddings();
		removeRightPaddings();
		removeTopPaddings();
		removeBottomPaddings();
	}
	
	//method
	public final void removeRightBorderColors() {
		rightBorderColor.setUndefined();
	}
	
	//method
	public final void removeRightBorderThicknesses() {
		rightBorderThickness.setUndefined();
	}
	
	//method
	public final void removeRightPaddings() {
		rightPadding.setUndefined();
	}
	
	//method
	public final void removeScrollBarColors() {
		scrollBarColor.setUndefined();
	}
	
	//method
	public final void removeScrollBarHoverColors() {
		scrollBarHoverColor.setUndefined();
	}
	
	//method
	public final void removeScrollBarMoveColors() {
		scrollBarMoveColor.setUndefined();
	}
	
	//method
	public final void removeScrollCursorColors() {
		scrollCursorColor.setUndefined();
	}
	
	//method
	public final void removeScrollCursorHoverColors() {
		scrollCursorHoverColor.setUndefined();
	}
	
	//method
	public final void removeScrollCursorMoveColors() {
		scrollCursorMoveColor.setUndefined();
	}
	
	//method
	public final void removeTopBorderColors() {
		topBorderColor.setUndefined();
	}
	
	//method
	public final void removeTopBorderThicknesses() {
		topBorderThickness.setUndefined();
	}
	
	//method
	public final void removeTopPaddings() {
		topPadding.setUndefined();
	}
	
	//method
	public final BWL setBackgroundColorForState(final ControlState state, final Color backgroundColor) {
		
		final var lBackground = new Background();
		lBackground.setColor(backgroundColor);
		
		this.background.setValueForState(state, lBackground);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBackgroundColorGradientForState(
		final ControlState state,
		final ColorGradient backgroundColorGradient
	) {
		
		final var lBackground = new Background();
		lBackground.setColorGradient(backgroundColorGradient);
		
		this.background.setValueForState(state, lBackground);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBackgroundImageForState(
		final ControlState state,
		final MutableImage backgroundImage,
		final ImageApplication imageApplication
	) {
		
		final var lBackground = new Background();
		lBackground.setImage(backgroundImage, imageApplication);
		
		this.background.setValueForState(state, lBackground);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBorderColorForState(final ControlState state, final Color borderColor) {
		
		this.borderColor.setValueForState(state, borderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBorderThicknessForState(final ControlState state, final int borderThickness) {
		
		this.borderThickness.setValueForState(state, borderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBottomBorderColorForState(final ControlState state, final Color bottomBorderColor) {
		
		this.bottomBorderColor.setValueForState(state, bottomBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBottomBorderThicknessForState(final ControlState state, final int bottomBorderThickness) {
		
		GlobalValidator.assertThat(bottomBorderThickness).thatIsNamed("bottom border thickness").isNotNegative();
		
		this.bottomBorderThickness.setValueForState(state, bottomBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBottomPaddingForState(final ControlState state, final int bottomPadding) {
		
		GlobalValidator.assertThat(bottomPadding).thatIsNamed("bottom padding").isNotNegative();
		
		this.bottomPadding.setValueForState(state, bottomPadding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setLeftBorderColorForState(final ControlState state, final Color leftBorderColor) {
		
		this.leftBorderColor.setValueForState(state, leftBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setLeftBorderThicknessForState(final ControlState state, final int leftBorderThickness) {
		
		GlobalValidator.assertThat(leftBorderThickness).thatIsNamed("left border thickness").isNotNegative();
		
		this.leftBorderThickness.setValueForState(state, leftBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setLeftPaddingForState(final ControlState state, final int leftPadding) {
		
		GlobalValidator.assertThat(leftPadding).thatIsNamed("left padding").isNotNegative();
		
		this.leftPadding.setValueForState(state, leftPadding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setPaddingForState(final ControlState state, final int padding) {
		
		this.padding.setValueForState(state, padding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setRightBorderColorForState(final ControlState state, final Color rightBorderColor) {
		
		this.rightBorderColor.setValueForState(state, rightBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setRightBorderThicknessForState(final ControlState state, final int rightBorderThickness) {
		
		GlobalValidator.assertThat(rightBorderThickness).thatIsNamed("right border thickness").isNotNegative();
		
		this.rightBorderThickness.setValueForState(state, rightBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setRightPaddingForState(final ControlState state, final int rightPadding) {
		
		GlobalValidator.assertThat(rightPadding).thatIsNamed("right padding").isNotNegative();
		
		this.rightPadding.setValueForState(state, rightPadding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollBarColorForState(final ControlState state, final Color scrollBarColor) {
		
		this.scrollBarColor.setValueForState(state, scrollBarColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollBarHoverColorForState(final ControlState state, final Color scrollBarHoverColor) {
		
		this.scrollBarHoverColor.setValueForState(state, scrollBarHoverColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollBarMoveColorForState(final ControlState state, final Color scrollBarMoveColor) {
		
		this.scrollBarMoveColor.setValueForState(state, scrollBarMoveColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollCursorColorForState(final ControlState state, final Color scrollCursorColor) {
		
		this.scrollCursorColor.setValueForState(state, scrollCursorColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollCursorHoverColorForState(final ControlState state, final Color scrollCursorHoverColor) {
		
		this.scrollCursorHoverColor.setValueForState(state, scrollCursorHoverColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollCursorMoveColorForState(final ControlState state, final Color scrollCursorMoveColor) {
		
		this.scrollCursorMoveColor.setValueForState(state, scrollCursorMoveColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setTopBorderColorForState(final ControlState state, final Color topBorderColor) {
		
		this.topBorderColor.setValueForState(state, topBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setTopBorderThicknessForState(final ControlState state, final int topBorderThickness) {
		
		GlobalValidator.assertThat(topBorderThickness).thatIsNamed("top border thickness").isNotNegative();
		
		this.topBorderThickness.setValueForState(state, topBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setTopPaddingForState(final ControlState state, final int topPadding) {
		
		GlobalValidator.assertThat(topPadding).thatIsNamed("top padding").isNotNegative();
		
		this.topPadding.setValueForState(state, topPadding);
		
		return asConcrete();
	}
}
