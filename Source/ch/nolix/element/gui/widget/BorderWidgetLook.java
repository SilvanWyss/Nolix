//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.formatelement.NonCascadingProperty;
import ch.nolix.element.gui.base.WidgetLook;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.color.ColorGradient;
import ch.nolix.element.gui.image.Background;
import ch.nolix.element.gui.image.Image;
import ch.nolix.element.gui.image.ImageApplication;

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
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> leftBorderThickness =
	new NonCascadingProperty<>(
		LEFT_BORDER_THICKNESS_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setLeftBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> rightBorderThickness =
	new NonCascadingProperty<>(
		RIGHT_BORDER_THICKNESS_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setRightBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> topBorderThickness =
	new NonCascadingProperty<>(
		TOP_BORDER_THICKNESS_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setTopBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> bottomBorderThickness =
	new NonCascadingProperty<>(
		BOTTOM_BORDER_THICKNESS_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setBottomBorderThicknessForState,
		DEFAULT_BORDER_THICKNESS
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> leftBorderColor =
	new NonCascadingProperty<>(
		LEFT_BORDER_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> rightBorderColor =
	new NonCascadingProperty<>(
		RIGHT_BORDER_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> topBorderColor =
	new NonCascadingProperty<>(
		TOP_BORDER_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> bottomBorderColor =
	new NonCascadingProperty<>(
		BOTTOM_BORDER_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Background> background =
	new NonCascadingProperty<>(
		BACKGROUND_HEADER,
		WidgetLookState.class,
		Background::fromSpecification,
		Background::getSpecification
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> leftPadding =
	new NonCascadingProperty<>(
		LEFT_PADDING_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setLeftPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> rightPadding =
	new NonCascadingProperty<>(
		RIGHT_PADDING_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setRightPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> topPadding =
	new NonCascadingProperty<>(
		TOP_PADDING_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setTopPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Integer> bottomPadding =
	new NonCascadingProperty<>(
		BOTTOM_PADDING_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		this::setBottomPaddingForState,
		DEFAULT_PADDING
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> scrollBarColor =
	new NonCascadingProperty<>(
		SCROLL_BAR_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollBarColorForState
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> scrollBarHoverColor =
	new NonCascadingProperty<>(
		SCROLL_BAR_HOVER_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollBarHoverColorForState
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> scrollBarMoveColor =
	new NonCascadingProperty<>(
		SCROLL_BAR_MOVE_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollBarMoveColorForState
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> scrollCursorColor =
	new NonCascadingProperty<>(
		SCROLL_CURSOR_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollCursorColorForState,
		DEFAULT_SCROLL_CURSOR_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> scrollCursorHoverColor =
	new NonCascadingProperty<>(
		SCROLL_CURSOR_HOVER_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollCursorHoverColorForState,
		DEFAULT_SCROLL_CURSOR_HOVER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<WidgetLookState, Color> scrollCursorMoveColor =
	new NonCascadingProperty<>(
		SCROLL_CURSOR_MOVE_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		this::setScrollCursorMoveColorForState,
		DEFAULT_SCROLL_CURSOR_MOVE_COLOR
	);
	
	//method
	public final Color getBackgroundColor() {
		return background.getValue().getBackgroundColor();
	}
	
	//method
	public final ColorGradient getBackgroundColorGradient() {
		return background.getValue().getBackgroundColorGradient();
	}
	
	//method
	public final Image getBackgroundImage() {
		return background.getValue().getBackgroundImage();
	}
	
	//method
	public final ImageApplication getBackgroundImageApplication() {
		return background.getValue().getBackgroundImageApplication();
	}
	
	//method
	public final Color getBottomBorderColor() {
		return bottomBorderColor.getValue();
	}
	
	//method
	public final int getBottomBorderThickenss() {
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
	public final int getLeftBorderThickenss() {
		return leftBorderThickness.getValue();
	}
	
	//method
	public final int getLeftPadding() {
		return leftPadding.getValue();
	}
	
	//method
	public final Color getRightBorderColor() {
		return rightBorderColor.getValue();
	}
	
	//method
	public final int getRightBorderThickenss() {
		return rightBorderThickness.getValue();
	}
	
	//method
	public final int getRightPadding() {
		return rightPadding.getValue();
	}
	
	//method
	public final Color getScrollBarColor() {
		return scrollBarColor.getValue();
	}
	
	//method
	public final Color getScrollBarHoverColor() {
		return scrollBarHoverColor.getValue();
	}
	
	//method
	public final Color getScrollBarMoveColor() {
		return scrollBarMoveColor.getValue();
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
	public final int getTopBorderThickenss() {
		return topBorderThickness.getValue();
	}
	
	//method
	public final int getTopPadding() {
		return topPadding.getValue();
	}
	
	//method
	public final boolean hasBackgroundColor() {
		return background.hasValue() && background.getValue().hasBackgroundColor();
	}
	
	//method
	public final boolean hasBackgroundColorGradient() {
		return background.hasValue() && background.getValue().hasBackgroundColorGradient();
	}
	
	//method
	public final boolean hasBackgroundImage() {
		return background.hasValue() && background.getValue().hasBackgroundImage();
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
	public final BWL setBackgroundColorForState(final WidgetLookState state, final Color backgroundColor) {
		
		final var lBackground = new Background();
		lBackground.setBackgroundColor(backgroundColor);
		
		this.background.setValueForState(state, lBackground);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBackgroundColorGradientForState(
		final WidgetLookState state,
		final ColorGradient backgroundColorGradient
	) {
		
		final var lBackground = new Background();
		lBackground.setBackgroundColorGradient(backgroundColorGradient);
		
		this.background.setValueForState(state, lBackground);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBackgroundImageForState(
		final WidgetLookState state,
		final Image backgroundImage,
		final ImageApplication imageApplication
	) {
		
		final var lBackground = new Background();
		lBackground.setBackgroundImage(backgroundImage, imageApplication);
		
		this.background.setValueForState(state, lBackground);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBorderThicknessesForState(final WidgetLookState state, final int borderThickness) {
		
		setLeftBorderThicknessForState(state, borderThickness);
		setRightBorderThicknessForState(state, borderThickness);
		setTopBorderThicknessForState(state, borderThickness);
		setBottomBorderThicknessForState(state, borderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBottomBorderColorForState(final WidgetLookState state, final Color bottomBorderColor) {
		
		this.bottomBorderColor.setValueForState(state, bottomBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBottomBorderThicknessForState(final WidgetLookState state, final int bottomBorderThickness) {
		
		Validator.assertThat(bottomBorderThickness).thatIsNamed("bottom border thickness").isNotNegative();
		
		this.bottomBorderThickness.setValueForState(state, bottomBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setBottomPaddingForState(final WidgetLookState state, final int bottomPadding) {
		
		Validator.assertThat(bottomPadding).thatIsNamed("bottom padding").isNotNegative();
		
		this.bottomPadding.setValueForState(state, bottomPadding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setLeftBorderColorForState(final WidgetLookState state, final Color leftBorderColor) {
		
		this.leftBorderColor.setValueForState(state, leftBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setLeftBorderThicknessForState(final WidgetLookState state, final int leftBorderThickness) {
		
		Validator.assertThat(leftBorderThickness).thatIsNamed("left border thickness").isNotNegative();
		
		this.leftBorderThickness.setValueForState(state, leftBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setLeftPaddingForState(final WidgetLookState state, final int leftPadding) {
		
		Validator.assertThat(leftPadding).thatIsNamed("left padding").isNotNegative();
		
		this.leftPadding.setValueForState(state, leftPadding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setPaddingForState(final WidgetLookState state, final int padding) {
		
		setLeftPaddingForState(state, padding);
		setRightPaddingForState(state, padding);
		setTopPaddingForState(state, padding);
		setBottomPaddingForState(state, padding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setRightBorderColorForState(final WidgetLookState state, final Color rightBorderColor) {
		
		this.rightBorderColor.setValueForState(state, rightBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setRightBorderThicknessForState(final WidgetLookState state, final int rightBorderThickness) {
		
		Validator.assertThat(rightBorderThickness).thatIsNamed("right border thickness").isNotNegative();
		
		this.rightBorderThickness.setValueForState(state, rightBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setRightPaddingForState(final WidgetLookState state, final int rightPadding) {
		
		Validator.assertThat(rightPadding).thatIsNamed("right padding").isNotNegative();
		
		this.rightPadding.setValueForState(state, rightPadding);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollBarColorForState(final WidgetLookState state, final Color scrollBarColor) {
		
		this.scrollBarColor.setValueForState(state, scrollBarColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollBarHoverColorForState(final WidgetLookState state, final Color scrollBarHoverColor) {
		
		this.scrollBarHoverColor.setValueForState(state, scrollBarHoverColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollBarMoveColorForState(final WidgetLookState state, final Color scrollBarMoveColor) {
		
		this.scrollBarMoveColor.setValueForState(state, scrollBarMoveColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollCursorColorForState(final WidgetLookState state, final Color scrollCursorColor) {
		
		this.scrollCursorColor.setValueForState(state, scrollCursorColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollCursorHoverColorForState(final WidgetLookState state, final Color scrollCursorHoverColor) {
		
		this.scrollCursorHoverColor.setValueForState(state, scrollCursorHoverColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setScrollCursorMoveColorForState(final WidgetLookState state, final Color scrollCursorMoveColor) {
		
		this.scrollCursorMoveColor.setValueForState(state, scrollCursorMoveColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setTopBorderColorForState(final WidgetLookState state, final Color topBorderColor) {
		
		this.topBorderColor.setValueForState(state, topBorderColor);
		
		return asConcrete();
	}
	
	//method
	public final BWL setTopBorderThicknessForState(final WidgetLookState state, final int topBorderThickness) {
		
		Validator.assertThat(topBorderThickness).thatIsNamed("top border thickness").isNotNegative();
		
		this.topBorderThickness.setValueForState(state, topBorderThickness);
		
		return asConcrete();
	}
	
	//method
	public final BWL setTopPaddingForState(final WidgetLookState state, final int topPadding) {
		
		Validator.assertThat(topPadding).thatIsNamed("top padding").isNotNegative();
		
		this.topPadding.setValueForState(state, topPadding);
		
		return asConcrete();
	}
}
