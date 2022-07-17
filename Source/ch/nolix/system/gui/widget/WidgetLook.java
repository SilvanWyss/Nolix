//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.formatelement.CascadingProperty;
import ch.nolix.system.formatelement.FormatElement;
import ch.nolix.system.formatelement.NonCascadingProperty;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.textformatapi.Font;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidgetLook;

//class
public abstract class WidgetLook<WL extends WidgetLook<WL>>
extends FormatElement<WL, ControlState>
implements IWidgetLook<WL> {
	
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
	private static final String FONT_HEADER = PascalCaseCatalogue.FONT;
	
	//constant
	private static final String BOLD_TEXT_FLAG_HEADER = "BoldTextag";
	
	//constant
	private static final String TEXT_SIZE_HEADER = PascalCaseCatalogue.TEXT_SIZE;
	
	//constant
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
	//attribute
	private final NonCascadingProperty<ControlState, Double> opacity =
	new NonCascadingProperty<>(
		PascalCaseCatalogue.OPACITY,
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
	private final CascadingProperty<ControlState, Color> textColor =
	new CascadingProperty<>(
		TEXT_COLOR_HEADER,
		ControlState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_TEXT_COLOR
	);
	
	//constructor
	protected WidgetLook() {
		super(ControlState.BASE);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public final <WL2 extends IWidgetLook<WL2>> void addChild(final WL2 widgetLook) {
		internalAddChild((WL)widgetLook);
	}
	
	//method
	public final boolean getBoldTextFlag() {
		return boldTextFlag.getValue();
	}
	
	//method
	public final Font getFont() {
		return font.getValue();
	}
	
	//method
	public final double getOpacity() {
		return opacity.getValue();
	}
	
	//method
	public final Color getTextColor() {
		return textColor.getValue();
	}
	
	//method
	public final int getTextSize() {
		return textSize.getValue();
	}
	
	//method
	public final void removeBoldTextFlags() {
		boldTextFlag.setUndefined();
	}
	
	//method
	public final void removeFonts() {
		font.setUndefined();
	}
	
	//method
	public final void removeTextColors() {
		textColor.setUndefined();
	}
	
	//method
	public final void removeTextSizes() {
		textSize.setUndefined();
	}
	
	//method
	public final WL setBoldTextFlagForState(final ControlState state, final boolean boldTextFlag) {
		
		this.boldTextFlag.setValueForState(state, boldTextFlag);
		
		return asConcrete();
	}
	
	//method
	public final WL setFontForState(final ControlState state, final Font font) {
		
		this.font.setValueForState(state, font);
		
		return asConcrete();
	}
	
	//method
	public final WL setOpacityForState(final ControlState state, final double opacity) {
		
		GlobalValidator.assertThat(opacity).thatIsNamed(LowerCaseCatalogue.OPACITY).isBetween(0.0, 1.0);
		
		this.opacity.setValueForState(state, opacity);
		
		return asConcrete();
	}
	
	//method
	public final WL setTextColorForState(final ControlState state, final Color textColor) {
		
		this.textColor.setValueForState(state, textColor);
		
		return asConcrete();
	}
	
	//method
	public final WL setTextSizeForState(final ControlState state, final int textSize) {
		
		GlobalValidator.assertThat(textSize).thatIsNamed(LowerCaseCatalogue.TEXT_SIZE).isPositive();
		
		this.textSize.setValueForState(state, textSize);
		
		return asConcrete();
	}
	
	//method
	final void setState(final ControlState state) {
		internalSwitchToState(state);
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
