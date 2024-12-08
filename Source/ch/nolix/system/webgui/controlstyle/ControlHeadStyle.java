package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.element.multistateconfiguration.MultiStateConfiguration;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.fontapi.Font;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlHeadStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

abstract class ControlHeadStyle<CS extends IControlHeadStyle<CS>>
extends MultiStateConfiguration<CS, ControlState>
implements IControlHeadStyle<CS> {

  public static final double DEFAULT_OPACITY = 1.0;

  public static final Font DEFAULT_FONT = Font.ARIAL;

  public static final boolean DEFAULT_BOLD_TEXT_FLAG = false;

  public static final int DEAULT_TEXT_SIZE = 20;

  public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

  private final NonCascadingProperty<ControlState, Double> opacity = //
  new NonCascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalogue.OPACITY_HEADER,
    ControlState.class,
    s -> getOpacityFromString(s.getSingleChildNodeHeader()),
    Node::withChildNode,
    this::setOpacityForState,
    DEFAULT_OPACITY);

  private final CascadingProperty<ControlState, Font> font = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalogue.FONT_HEADER,
    ControlState.class,
    Font::fromSpecification,
    Node::fromEnum,
    DEFAULT_FONT);

  private final CascadingProperty<ControlState, Boolean> boldTextFlag = //
  CascadingProperty
    .forBooleanWithNameAndStateClassAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalogue.BOLD_TEXT_FLAG_HEADER,
      ControlState.class,
      DEFAULT_BOLD_TEXT_FLAG);

  private final CascadingProperty<ControlState, Integer> textSize = //
  CascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalogue.TEXT_SIZE_HEADER,
      ControlState.class,
      this::setTextSizeForState,
      DEAULT_TEXT_SIZE);

  private final CascadingProperty<ControlState, IColor> textColor = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalogue.TEXT_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_TEXT_COLOR);

  protected ControlHeadStyle() {
    super(ControlState.BASE);
  }

  @Override
  @SuppressWarnings("unchecked")
  public final <CS2 extends IControlHeadStyle<CS2>> void addChild(final CS2 controlStyle) {
    internalAddChild((CS) controlStyle);
  }

  @Override
  public final boolean getBoldTextFlagWhenHasState(final ControlState state) {
    return boldTextFlag.getValueWhenHasState(state);
  }

  @Override
  public final Font getFontWhenHasState(final ControlState state) {
    return font.getValueWhenHasState(state);
  }

  @Override
  public final double getOpacityWhenHasState(final ControlState state) {
    return opacity.getValueWhenHasState(state);
  }

  @Override
  public final IColor getTextColorWhenHasState(final ControlState state) {
    return textColor.getValueWhenHasState(state);
  }

  @Override
  public final int getTextSizeWhenHasState(final ControlState state) {
    return textSize.getValueWhenHasState(state);
  }

  @Override
  public final void removeCustomBoldTextFlags() {
    boldTextFlag.setUndefined();
  }

  @Override
  public final void removeCustomFonts() {
    font.setUndefined();
  }

  @Override
  public final void removeCustomOpacities() {
    opacity.setUndefined();
  }

  @Override
  public final void removeCustomTextColors() {
    textColor.setUndefined();
  }

  @Override
  public final void removeCustomTextSizes() {
    textSize.setUndefined();
  }

  @Override
  public final CS setBoldTextFlagForState(final ControlState state, final boolean boldTextFlag) {

    this.boldTextFlag.setValueForState(state, boldTextFlag);

    return asConcrete();
  }

  @Override
  public final CS setFontForState(final ControlState state, final Font font) {

    this.font.setValueForState(state, font);

    return asConcrete();
  }

  @Override
  public final CS setOpacityForState(final ControlState state, final double opacity) {

    GlobalValidator.assertThat(opacity).thatIsNamed(LowerCaseVariableCatalogue.OPACITY).isBetween(0.0, 1.0);

    this.opacity.setValueForState(state, opacity);

    return asConcrete();
  }

  @Override
  public final CS setTextColorForState(final ControlState state, final IColor textColor) {

    this.textColor.setValueForState(state, textColor);

    return asConcrete();
  }

  @Override
  public final CS setTextSizeForState(final ControlState state, final int textSize) {

    GlobalValidator.assertThat(textSize).thatIsNamed(LowerCaseVariableCatalogue.TEXT_SIZE).isPositive();

    this.textSize.setValueForState(state, textSize);

    return asConcrete();
  }

  private double getOpacityFromString(final String string) {

    GlobalValidator.assertThat(string).thatIsNamed(String.class).isNotNull();

    if (!string.endsWith("%")) {
      return Double.valueOf(string);
    }

    return (Double.valueOf(string.substring(0, string.length() - 1)) / 100);
  }
}
