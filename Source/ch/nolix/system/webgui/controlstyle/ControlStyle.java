//package declaration
package ch.nolix.system.webgui.controlstyle;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.multistateelement.CascadingProperty;
import ch.nolix.system.element.multistateelement.MultiStateElement;
import ch.nolix.system.element.multistateelement.NonCascadingProperty;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.textformatapi.Font;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//class
public abstract class ControlStyle<CS extends ControlStyle<CS>>
extends MultiStateElement<CS, ControlState>
implements IControlStyle<CS> {

	//constant
	public static final double DEFAULT_OPACITY = 1.0;
	
	//constant
	public static final Font DEFAULT_FONT = Font.ARIAL;
	
	//constant
	public static final boolean DEFAULT_BOLD_TEXT_FLAG = false;
	
	//constant
	public static final int DEAULT_TEXT_SIZE = 20;
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String OPACITY_HEADER = PascalCaseCatalogue.OPACITY;
	
	//constant
	private static final String FONT_HEADER = PascalCaseCatalogue.FONT;
	
	//constant
	private static final String BOLD_TEXT_FLAG_HEADER = "BoldText";
	
	//constant
	private static final String TEXT_SIZE_HEADER = PascalCaseCatalogue.TEXT_SIZE;
	
	//constant
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attribute
	private final NonCascadingProperty<ControlState, Double> opacity =
	new NonCascadingProperty<>(
		OPACITY_HEADER,
		ControlState.class,
		s -> getOpacityFromString(s.getSingleChildNodeHeader()),
		Node::withChildNode,
		this::setOpacityForState,
		DEFAULT_OPACITY
	);
	
	//attribute
	private final CascadingProperty<ControlState, Font> font =
	new CascadingProperty<>(
		FONT_HEADER,
		ControlState.class,
		Font::fromSpecification,
		Node::fromEnum,
		DEFAULT_FONT
	);
	
	//attribute
	private final CascadingProperty<ControlState, Boolean> boldTextFlag =
	new CascadingProperty<>(
		BOLD_TEXT_FLAG_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsBoolean,
		Node::withChildNode,
		DEFAULT_BOLD_TEXT_FLAG
	);
	
	//attribute
	private final CascadingProperty<ControlState, Integer> textSize =
	new CascadingProperty<>(
		TEXT_SIZE_HEADER,
		ControlState.class,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode,
		this::setTextSizeForState,
		DEAULT_TEXT_SIZE
	);
	
	//attribute
	private final CascadingProperty<ControlState, IColor> textColor =
	new CascadingProperty<>(
		TEXT_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		IColor::getSpecification,
		DEFAULT_TEXT_COLOR
	);
	
	//constructor
	protected ControlStyle() {
		super(ControlState.BASE);
	}
	
	//method
	@Override
	public final boolean getBoldTextFlagOfState(final ControlState state) {
		return boldTextFlag.getValueOfState(state);
	}
	
	//method
	@Override
	public final Font getFontOfState(final ControlState state) {
		return font.getValueOfState(state);
	}
	
	//method
	@Override
	public final double getOpacityOfState(final ControlState state) {
		return opacity.getValueOfState(state);
	}
	
	//method
	@Override
	public final IColor getTextColorOfState(final ControlState state) {
		return textColor.getValueOfState(state);
	}
	
	//method
	@Override
	public final int getTextSizeOfState(final ControlState state) {
		return textSize.getValueOfState(state);
	}
	
	//method
	@Override
	public final void removeCustomBoldTextFlags() {
		boldTextFlag.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomFonts() {
		font.setUndefined();
	}
	
	//method
	@Override
	public void removeCustomOpacities() {
		opacity.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomTextColors() {
		textColor.setUndefined();
	}
	
	//method
	@Override
	public final void removeCustomTextSizes() {
		textSize.setUndefined();
	}
	
	//method
	@Override
	public final CS setBoldTextFlagForState(final ControlState state, final boolean boldTextFlag) {
		
		this.boldTextFlag.setValueForState(state, boldTextFlag);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final CS setFontForState(final ControlState state, final Font font) {
		
		this.font.setValueForState(state, font);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final CS setOpacityForState(final ControlState state, final double opacity) {
		
		GlobalValidator.assertThat(opacity).thatIsNamed(LowerCaseCatalogue.OPACITY).isBetween(0.0, 1.0);
		
		this.opacity.setValueForState(state, opacity);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final CS setTextColorForState(final ControlState state, final IColor textColor) {
		
		this.textColor.setValueForState(state, textColor);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final CS setTextSizeForState(final ControlState state, final int textSize) {
		
		GlobalValidator.assertThat(textSize).thatIsNamed(LowerCaseCatalogue.TEXT_SIZE).isPositive();
		
		this.textSize.setValueForState(state, textSize);
		
		return asConcrete();
	}
	
	//method
	private double getOpacityFromString(final String string) {
		
		GlobalValidator.assertThat(string).thatIsNamed(String.class).isNotNull();
		
		if (!string.endsWith("%")) {
			return Double.valueOf(string);
		}
		
		return (Double.valueOf(string.substring(0, string.length() - 1)) / 100);
	}
}
