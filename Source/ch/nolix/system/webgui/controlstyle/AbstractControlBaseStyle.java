package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.element.multistateconfiguration.AbstractMultiStateConfiguration;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.font.Font;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.webgui.controlstyle.IControlBaseStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

abstract class AbstractControlBaseStyle<C extends IControlBaseStyle<C>>
extends AbstractMultiStateConfiguration<C, ControlState>
implements IControlBaseStyle<C> {
  public static final double DEFAULT_OPACITY = 1.0;

  public static final Font DEFAULT_FONT = Font.ARIAL;

  public static final boolean DEFAULT_BOLD_TEXT_FLAG = false;

  public static final int DEAULT_TEXT_SIZE = 20;

  public static final LineDecoration DEFAULT_TEXT_LINE_DECORATION = LineDecoration.UNDERLINE;

  public static final Color DEFAULT_TEXT_COLOR = X11ColorCatalog.BLACK;

  private final NonCascadingProperty<ControlState, Double> memberOpacity = //
  new NonCascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.OPACITY_HEADER,
    ControlState.class,
    s -> getOpacityFromString(s.getSingleChildNodeHeader()),
    Node::withChildNode,
    this::forStateSetOpacity,
    DEFAULT_OPACITY);

  private final CascadingProperty<ControlState, Font> memberFont = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.FONT_HEADER,
    ControlState.class,
    Font::fromSpecification,
    Node::fromEnum,
    DEFAULT_FONT);

  private final CascadingProperty<ControlState, Boolean> memberBoldTextFlag = //
  CascadingProperty
    .forBooleanWithNameAndStateClassAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalog.BOLD_TEXT_FLAG_HEADER,
      ControlState.class,
      DEFAULT_BOLD_TEXT_FLAG);

  private final CascadingProperty<ControlState, Integer> memberTextSize = //
  CascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalog.TEXT_SIZE_HEADER,
      ControlState.class,
      this::forStateSetTextSize,
      DEAULT_TEXT_SIZE);

  private final CascadingProperty<ControlState, LineDecoration> memberTextLineDecoration = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.TEXT_LINE_DECORATION_HEADER,
    ControlState.class,
    LineDecoration::fromSpecification,
    Node::fromEnum,
    DEFAULT_TEXT_LINE_DECORATION);

  private final CascadingProperty<ControlState, IColor> memberTextColor = //
  new CascadingProperty<>(
    ControlHeadStyleAttributeHeaderCatalog.TEXT_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_TEXT_COLOR);

  protected AbstractControlBaseStyle() {
    super(ControlState.BASE);

    memberTextLineDecoration.setUndefined();
  }

  @Override
  @SuppressWarnings("unchecked")
  public final <C2 extends IControlBaseStyle<C2>> void addChild(final C2 controlStyle) {
    internalAddChild((C) controlStyle);
  }

  @Override
  public boolean definesTextLineDecorationForState(final ControlState state) {
    return memberTextLineDecoration.hasValueForState(state);
  }

  @Override
  public final boolean getBoldTextFlagWhenHasState(final ControlState state) {
    return memberBoldTextFlag.getValueWhenHasState(state);
  }

  @Override
  public final Font getFontWhenHasState(final ControlState state) {
    return memberFont.getValueWhenHasState(state);
  }

  @Override
  public final double getOpacityWhenHasState(final ControlState state) {
    return memberOpacity.getValueWhenHasState(state);
  }

  @Override
  public final IColor getTextColorWhenHasState(final ControlState state) {
    return memberTextColor.getValueWhenHasState(state);
  }

  @Override
  public LineDecoration getTextLineDecorationWhenHasState(ControlState state) {
    return memberTextLineDecoration.getValueWhenHasState(state);
  }

  @Override
  public final int getTextSizeWhenHasState(final ControlState state) {
    return memberTextSize.getValueWhenHasState(state);
  }

  @Override
  public final void removeCustomBoldTextFlags() {
    memberBoldTextFlag.setUndefined();
  }

  @Override
  public final void removeCustomFonts() {
    memberFont.setUndefined();
  }

  @Override
  public final void removeCustomOpacities() {
    memberOpacity.setUndefined();
  }

  @Override
  public final void removeCustomTextColors() {
    memberTextColor.setUndefined();
  }

  @Override
  public void removeCustomTextLineDecorations() {
    memberTextLineDecoration.setUndefined();
  }

  @Override
  public final void removeCustomTextSizes() {
    memberTextSize.setUndefined();
  }

  @Override
  public final C forStateSetBoldTextFlag(final ControlState state, final boolean boldTextFlag) {
    memberBoldTextFlag.setValueForState(state, boldTextFlag);

    return asConcrete();
  }

  @Override
  public final C forStateSetFont(final ControlState state, final Font font) {
    memberFont.setValueForState(state, font);

    return asConcrete();
  }

  @Override
  public final C forStateSetOpacity(final ControlState state, final double opacity) {
    Validator.assertThat(opacity).thatIsNamed(LowerCaseVariableCatalog.OPACITY).isBetween(0.0, 1.0);

    memberOpacity.setValueForState(state, opacity);

    return asConcrete();
  }

  @Override
  public final C forStateSetTextColor(final ControlState state, final IColor textColor) {
    memberTextColor.setValueForState(state, textColor);

    return asConcrete();
  }

  @Override
  public C forStateSetTextLineDecoration(final ControlState state, final LineDecoration textLineDecoration) {
    memberTextLineDecoration.setValueForState(state, textLineDecoration);

    return asConcrete();
  }

  @Override
  public final C forStateSetTextSize(final ControlState state, final int textSize) {
    Validator.assertThat(textSize).thatIsNamed(LowerCaseVariableCatalog.TEXT_SIZE).isPositive();

    memberTextSize.setValueForState(state, textSize);

    return asConcrete();
  }

  private double getOpacityFromString(final String string) {
    Validator.assertThat(string).thatIsNamed(String.class).isNotNull();

    if (!string.endsWith("%")) {
      return Double.valueOf(string);
    }

    return (Double.valueOf(string.substring(0, string.length() - 1)) / 100);
  }
}
