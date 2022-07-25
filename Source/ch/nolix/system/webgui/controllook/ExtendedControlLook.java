//package declaration
package ch.nolix.system.webgui.controllook;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateelement.ForwardingProperty;
import ch.nolix.system.element.multistateelement.NonCascadingProperty;
import ch.nolix.system.gui.canvas.Background;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controllookapi.IExtendedControlLook;

//class
public abstract class ExtendedControlLook<ECL extends ExtendedControlLook<ECL>>
extends ControlLook<ECL>
implements IExtendedControlLook<ECL> {
	
	//constant
	public static final int DEFAULT_BORDER_THICKNESS = 0;
	
	//constant
	public static final IColor DEFAULT_BORDER_COLOR = Color.BLACK;
	
	//constant
	public static final int DEFAULT_PADDING = 0;
		
	//constant
	private static final String LEFT_BORDER_THICKNESS_HEADER = "LeftBorderThickness";
	
	//constant
	private static final String RIGHT_BORDER_THICKNESS_HEADER = "RightBorderThickness";
	
	//constant
	private static final String TOP_BORDER_THICKNESS_HEADER = "TopBorderThickness";
	
	//constant
	private static final String BOTTOM_BORDER_THICKNESS_HEADER = "BottomBorderThickness";
	
	//constant
	private static final String LEFT_BORDER_COLOR_HEADER = "LeftBorderColor";
	
	//constant
	private static final String RIGHT_BORDER_COLOR_HEADER = "RightBorderColor";
	
	//constant
	private static final String TOP_BORDER_COLOR_HEADER = "TopBorderColor";
	
	//constant
	private static final String BOTTOM_BORDER_COLOR_HEADER = "BottomBorderColor";
	
	//constant
	private static final String BACKGROUND_HEADER = "Background";
	
	//constant
	private static final String LEFT_PADDING_HEADER = "LeftPadding";
	
	//constant
	private static final String RIGHT_PADDING_HEADER = "RightPadding";
	
	//constant
	private static final String TOP_PADDING_HEADER = "TopPadding";
	
	//constant
	private static final String BOTTOM_PADDING_HEADER = "BottomPadding";
	
	//constant
	private static final String BORDER_COLOR_HEADER = "BorderColor";
	
	//constant
	private static final String BORDER_THICKNESS_HEADER = "BorderThickness";
	
	//constant
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
	private final NonCascadingProperty<ControlState, IColor> leftBorderColor =
	new NonCascadingProperty<>(
		LEFT_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		IColor::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, IColor> rightBorderColor =
	new NonCascadingProperty<>(
		RIGHT_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		IColor::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, IColor> topBorderColor =
	new NonCascadingProperty<>(
		TOP_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		IColor::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, IColor> bottomBorderColor =
	new NonCascadingProperty<>(
		BOTTOM_BORDER_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		IColor::getSpecification,
		DEFAULT_BORDER_COLOR
	);
	
	//attribute
	private final NonCascadingProperty<ControlState, IBackground> background =
	new NonCascadingProperty<>(
		BACKGROUND_HEADER,
		ControlState.class,
		Background::fromSpecification,
		IBackground::getSpecification
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
	private final ForwardingProperty<ControlState, Integer> borderThickness =
	new ForwardingProperty<>(
		BORDER_THICKNESS_HEADER,
		leftBorderThickness,
		rightBorderThickness,
		topBorderThickness,
		bottomBorderThickness
	);
	
	//attribute
	private final ForwardingProperty<ControlState, IColor> borderColor =
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
	@Override
	public final void removeCustomBackgrounds() {
		background.setUndefined();
	}
	
	//method
	@Override
	public void removeCustomBorderColors() {
		borderColor.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomBorderThicknesses() {
		removeCustomLeftBorderColors();
		removeCustomRightBorderColors();
		removeCustomTopBorderColors();
		removeCustomBottomBorderColors();
	}
	
	//method
	@Override
	public final void removeCustomBottomBorderColors() {
		bottomBorderColor.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomBottomBorderThicknesses() {
		bottomBorderThickness.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomBottomPaddings() {
		bottomPadding.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomLeftBorderColors() {
		leftBorderColor.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomLeftBorderThicknesses() {
		leftBorderThickness.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomLeftPaddings() {
		leftPadding.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomPaddings() {
		removeCustomLeftPaddings();
		removeCustomRightPaddings();
		removeCustomTopPaddings();
		removeCustomBottomPaddings();
	}
	
	//method
	@Override
	public final void removeCustomRightBorderColors() {
		rightBorderColor.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomRightBorderThicknesses() {
		rightBorderThickness.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomRightPaddings() {
		rightPadding.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomTopBorderColors() {
		topBorderColor.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomTopBorderThicknesses() {
		topBorderThickness.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomTopPaddings() {
		topPadding.setUndefined();
	}
		
	//method
	@Override
	public final ECL setBackgroundColorForState(final ControlState state, final IColor backgroundColor) {
		return setBackgroundForState(state, Background.withColor(backgroundColor));
	}
	
	//method
	@Override
	public final ECL setBackgroundColorGradientForState(
		final ControlState state,
		final IColorGradient backgroundColorGradient
	) {
		return setBackgroundForState(state, Background.withColorGradient(backgroundColorGradient));
	}
	
	@Override
	public ECL setBackgroundForState(ControlState state, IBackground background) {
		
		this.background.setValueForState(state, background);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setBackgroundImageForState(
		final ControlState state,
		final IImage backgroundImage,
		final ImageApplication imageApplication
	) {
		return setBackgroundForState(
			state,
			Background.withImageAndImageApplication(backgroundImage, imageApplication)
		);
	}
	
	//method
	@Override
	public final ECL setBorderColorForState(final ControlState state, final IColor borderColor) {
		
		this.borderColor.setValueForState(state, borderColor);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setBorderThicknessForState(final ControlState state, final int borderThickness) {
		
		this.borderThickness.setValueForState(state, borderThickness);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setBottomBorderColorForState(final ControlState state, final IColor bottomBorderColor) {
		
		this.bottomBorderColor.setValueForState(state, bottomBorderColor);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setBottomBorderThicknessForState(final ControlState state, final int bottomBorderThickness) {
		
		GlobalValidator.assertThat(bottomBorderThickness).thatIsNamed("bottom border thickness").isNotNegative();
		
		this.bottomBorderThickness.setValueForState(state, bottomBorderThickness);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setBottomPaddingForState(final ControlState state, final int bottomPadding) {
		
		GlobalValidator.assertThat(bottomPadding).thatIsNamed("bottom padding").isNotNegative();
		
		this.bottomPadding.setValueForState(state, bottomPadding);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setLeftBorderColorForState(final ControlState state, final IColor leftBorderColor) {
		
		this.leftBorderColor.setValueForState(state, leftBorderColor);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setLeftBorderThicknessForState(final ControlState state, final int leftBorderThickness) {
		
		GlobalValidator.assertThat(leftBorderThickness).thatIsNamed("left border thickness").isNotNegative();
		
		this.leftBorderThickness.setValueForState(state, leftBorderThickness);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setLeftPaddingForState(final ControlState state, final int leftPadding) {
		
		GlobalValidator.assertThat(leftPadding).thatIsNamed("left padding").isNotNegative();
		
		this.leftPadding.setValueForState(state, leftPadding);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setPaddingForState(final ControlState state, final int padding) {
		
		this.padding.setValueForState(state, padding);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setRightBorderColorForState(final ControlState state, final IColor rightBorderColor) {
		
		this.rightBorderColor.setValueForState(state, rightBorderColor);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setRightBorderThicknessForState(final ControlState state, final int rightBorderThickness) {
		
		GlobalValidator.assertThat(rightBorderThickness).thatIsNamed("right border thickness").isNotNegative();
		
		this.rightBorderThickness.setValueForState(state, rightBorderThickness);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setRightPaddingForState(final ControlState state, final int rightPadding) {
		
		GlobalValidator.assertThat(rightPadding).thatIsNamed("right padding").isNotNegative();
		
		this.rightPadding.setValueForState(state, rightPadding);
		
		return asConcrete();
	}
		
	//method
	@Override
	public final ECL setTopBorderColorForState(final ControlState state, final IColor topBorderColor) {
		
		this.topBorderColor.setValueForState(state, topBorderColor);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setTopBorderThicknessForState(final ControlState state, final int topBorderThickness) {
		
		GlobalValidator.assertThat(topBorderThickness).thatIsNamed("top border thickness").isNotNegative();
		
		this.topBorderThickness.setValueForState(state, topBorderThickness);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final ECL setTopPaddingForState(final ControlState state, final int topPadding) {
		
		GlobalValidator.assertThat(topPadding).thatIsNamed("top padding").isNotNegative();
		
		this.topPadding.setValueForState(state, topPadding);
		
		return asConcrete();
	}
}
