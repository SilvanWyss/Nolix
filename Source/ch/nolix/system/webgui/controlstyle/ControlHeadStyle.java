package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.element.multistateconfiguration.MultiStateConfiguration;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.fontapi.Font;
import ch.nolix.systemapi.guiapi.fontapi.LineDecoration;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlHeadStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

abstract class ControlHeadStyle<C extends IControlHeadStyle<C>>
extends MultiStateConfiguration<C, ControlState>
implements IControlHeadStyle<C> {

  public static final double DEFAULT_OPACITY = 1.0;

  public static final Font DEFAULT_FONT = Font.ARIAL;

  public static final boolean DEFAULT_BOLD_TEXT_FLAG = false;

  public static final int DEAULT_TEXT_SIZE = 20;

  public static final LineDecoration DEFAULT_TEXT_LINE_DECORATION = LineDecoration.UNDERLINE;

  public static final Color DEFAULT_TEXT_COLOR = X11ColorCatalog.BLACK;

  private final NonCascadingProperty<ControlState, Double> opacity = //
  new NonCascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.OPACITY_HEADER,
    ControlState.class,
    s -> getOpacityFromString(s.getSingleChildNodeHeader()),
    Node::withChildNode,
    this::setOpacityForState,
    DEFAULT_OPACITY);

  private final CascadingProperty<ControlState, Font> font = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.FONT_HEADER,
    ControlState.class,
    Font::fromSpecification,
    Node::fromEnum,
    DEFAULT_FONT);

  private final CascadingProperty<ControlState, Boolean> boldTextFlag = //
  CascadingProperty
    .forBooleanWithNameAndStateClassAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalog.BOLD_TEXT_FLAG_HEADER,
      ControlState.class,
      DEFAULT_BOLD_TEXT_FLAG);

  private final CascadingProperty<ControlState, Integer> textSize = //
  CascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalog.TEXT_SIZE_HEADER,
      ControlState.class,
      this::setTextSizeForState,
      DEAULT_TEXT_SIZE);

  private final CascadingProperty<ControlState, LineDecoration> textLineDecoration = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.TEXT_LINE_DECORATION_HEADER,
    ControlState.class,
    LineDecoration::fromSpecification,
    Node::fromEnum,
    DEFAULT_TEXT_LINE_DECORATION);

  private final CascadingProperty<ControlState, IColor> textColor = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.TEXT_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_TEXT_COLOR);

  protected ControlHeadStyle() {

    super(ControlState.BASE);

    textLineDecoration.setUndefined();
  }

  @Override
  @SuppressWarnings("unchecked")
  public final <C2 extends IControlHeadStyle<C2>> void addChild(final C2 controlStyle) {
    internalAddChild((C) controlStyle);
  }

  @Override
  public boolean definesTextLineDecorationForState(final ControlState state) {
    return textLineDecoration.hasValueForState(state);
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
  public LineDecoration getTextLineDecorationWhenHasState(ControlState state) {
    return textLineDecoration.getValueWhenHasState(state);
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
  public void removeCustomTextLineDecorations() {
    textLineDecoration.setUndefined();
  }

  @Override
  public final void removeCustomTextSizes() {
    textSize.setUndefined();
  }

  @Override
  public final C setBoldTextFlagForState(final ControlState state, final boolean boldTextFlag) {

    this.boldTextFlag.setValueForState(state, boldTextFlag);

    return asConcrete();
  }

  @Override
  public final C setFontForState(final ControlState state, final Font font) {

    this.font.setValueForState(state, font);

    return asConcrete();
  }

  @Override
  public final C setOpacityForState(final ControlState state, final double opacity) {

    GlobalValidator.assertThat(opacity).thatIsNamed(LowerCaseVariableCatalog.OPACITY).isBetween(0.0, 1.0);

    this.opacity.setValueForState(state, opacity);

    return asConcrete();
  }

  @Override
  public final C setTextColorForState(final ControlState state, final IColor textColor) {

    this.textColor.setValueForState(state, textColor);

    return asConcrete();
  }

  @Override
  public C setTextLineDecorationForState(final ControlState state, final LineDecoration textLineDecoration) {

    this.textLineDecoration.setValueForState(state, textLineDecoration);

    return asConcrete();
  }

  @Override
  public final C setTextSizeForState(final ControlState state, final int textSize) {

    GlobalValidator.assertThat(textSize).thatIsNamed(LowerCaseVariableCatalog.TEXT_SIZE).isPositive();

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
