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
	public final Color getLeftBorderColor() {
		return leftBorderColor.getValue();
	}
	
	//method
	public final int getLeftBorderThickenss() {
		return leftBorderThickness.getValue();
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
	public final Color getTopBorderColor() {
		return topBorderColor.getValue();
	}
	
	//method
	public final int getTopBorderThickenss() {
		return topBorderThickness.getValue();
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
	public final void removeBackground() {
		background.setUndefined();
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
	public final void removeLeftBorderColors() {
		leftBorderColor.setUndefined();
	}
	
	//method
	public final void removeLeftBorderThicknesses() {
		leftBorderThickness.setUndefined();
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
	public final void removeTopBorderColors() {
		topBorderColor.setUndefined();
	}
	
	//method
	public final void removeTopBorderThicknesses() {
		topBorderThickness.setUndefined();
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
}
