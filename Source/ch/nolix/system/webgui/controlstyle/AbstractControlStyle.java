package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.multistateconfiguration.ForwardingProperty;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeInt;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeIntValidator;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.background.Background;
import ch.nolix.system.gui.box.CornerShadow;
import ch.nolix.systemapi.element.multistateconfiguration.IMultiStateConfiguration;
import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.box.ICornerShadow;
import ch.nolix.systemapi.gui.colorgradient.IColorGradient;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public abstract //
class AbstractControlStyle< //NOSONAR: A AbstractControlStyle is a principal object thus it has many methods.
S extends IControlStyle<S> & IMultiStateConfiguration<S, ControlState>>
extends AbstractControlBaseStyle<S>
implements IControlStyle<S> {
  public static final int DEFAULT_CORNER_RADIUS = 0;

  public static final int DEFAULT_BORDER_THICKNESS = 0;

  public static final IColor DEFAULT_BORDER_COLOR = X11ColorCatalog.BLACK;

  public static final IBackground DEFAULT_BACKGROUND = Background.TRANSPARENT_BACKGROUND;

  public static final int DEFAULT_PADDING = 0;

  private static final String WIDTH_HEADER = PascalCaseVariableCatalog.WIDTH;

  private static final String HEIGHT_HEADER = PascalCaseVariableCatalog.HEIGHT;

  private static final String CORNER_RADIUS_HEADER = "CornerRadius";

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

  private static final String CORNER_SHADOWS_HEADER = "CornerShadows";

  private static final String BORDER_COLOR_HEADER = "BorderColor";

  private static final String BORDER_THICKNESS_HEADER = "BorderThickness";

  private static final String PADDING_HEADER = "Padding";

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> width = //
  new NonCascadingProperty<>(
    WIDTH_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setWidthForState,
    AbsoluteOrRelativeInt.withIntValue(100));

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> height = //
  new NonCascadingProperty<>(
    HEIGHT_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setHeightForState,
    AbsoluteOrRelativeInt.withIntValue(100));

  private final NonCascadingProperty<ControlState, Integer> memberCornerRadius = //
  NonCascadingProperty.forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
    CORNER_RADIUS_HEADER,
    ControlState.class,
    this::forStateSetCornerRadius,
    DEFAULT_CORNER_RADIUS);

  private final NonCascadingProperty<ControlState, Integer> memberLeftBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      LEFT_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::forStateSetLeftBorderThickness,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, Integer> memberRightBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      RIGHT_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::forStateSetRightBorderThickness,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, Integer> memberTopBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      TOP_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::forStateSetTopBorderThickness,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, Integer> memberBottomBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      BOTTOM_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::forStateSetBottomBorderThickness,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, IColor> memberLeftBorderColor = new NonCascadingProperty<>(
    LEFT_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IColor> memberRightBorderColor = new NonCascadingProperty<>(
    RIGHT_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IColor> memberTopBorderColor = new NonCascadingProperty<>(
    TOP_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IColor> memberBottomBorderColor = new NonCascadingProperty<>(
    BOTTOM_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IBackground> memberBackground = new NonCascadingProperty<>(
    BACKGROUND_HEADER,
    ControlState.class,
    Background::fromSpecification,
    IBackground::getSpecification,
    DEFAULT_BACKGROUND);

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> memberLeftPadding = //
  new NonCascadingProperty<>(
    LEFT_PADDING_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setLeftPaddingForState,
    AbsoluteOrRelativeInt.withIntValue(DEFAULT_PADDING));

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> memberRightPadding = //
  new NonCascadingProperty<>(
    RIGHT_PADDING_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setRightPaddingForState,
    AbsoluteOrRelativeInt.withIntValue(DEFAULT_PADDING));

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> memberTopPadding = //
  new NonCascadingProperty<>(
    TOP_PADDING_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setTopPaddingForState,
    AbsoluteOrRelativeInt.withIntValue(DEFAULT_PADDING));

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> memberBottomPadding = //
  new NonCascadingProperty<>(
    BOTTOM_PADDING_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setBottomPaddingForState,
    AbsoluteOrRelativeInt.withIntValue(DEFAULT_PADDING));

  private final NonCascadingProperty<ControlState, IContainer<CornerShadow>> cornerShadows = //
  new NonCascadingProperty<>(
    CORNER_SHADOWS_HEADER,
    ControlState.class,
    s -> s.getStoredChildNodes().to(CornerShadow::fromSpecification),
    s -> Node.withChildNodes(s.to(CornerShadow::getSpecification)),
    this::setCornerShadowsForState,
    ImmutableList.createEmpty());

  private final ForwardingProperty<ControlState, Integer> memberBorderThickness = //
  ForwardingProperty.withNameAndForProperty(
    BORDER_THICKNESS_HEADER,
    memberLeftBorderThickness,
    memberRightBorderThickness,
    memberTopBorderThickness,
    memberBottomBorderThickness);

  private final ForwardingProperty<ControlState, IColor> memberBorderColor = ForwardingProperty.withNameAndForProperty(
    BORDER_COLOR_HEADER,
    memberLeftBorderColor,
    memberRightBorderColor,
    memberTopBorderColor,
    memberBottomBorderColor);

  private final ForwardingProperty<ControlState, IAbsoluteOrRelativeInt> memberPadding = //
  ForwardingProperty.withNameAndForProperty(PADDING_HEADER, memberLeftPadding, memberRightPadding, memberTopPadding,
    memberBottomPadding);

  @Override
  public final boolean definesHeightForState(final ControlState state) {
    return height.hasValueForState(state);
  }

  @Override
  public final boolean definesWidthForState(final ControlState state) {
    return width.hasValueForState(state);
  }

  @Override
  public final IBackground getBackgroundWhenHasState(final ControlState state) {
    return memberBackground.getValueWhenHasState(state);
  }

  @Override
  public final IColor getBottomBorderColorWhenHasState(final ControlState state) {
    return memberBottomBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getBottomBorderThicknessWhenHasState(final ControlState state) {
    return memberBottomBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getBottomPaddingWhenHasState(final ControlState state) {
    return memberBottomPadding.getValueWhenHasState(state);
  }

  @Override
  public final int getCornerRadiusWhenHasState(final ControlState state) {
    return memberCornerRadius.getValueWhenHasState(state);
  }

  @Override
  public final IContainer<? extends ICornerShadow> getCornerShadowsWhenHasState(final ControlState state) {
    return cornerShadows.getValueWhenHasState(state);
  }

  @Override
  public final IColor getLeftBorderColorWhenHasState(final ControlState state) {
    return memberLeftBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getLeftBorderThicknessWhenHasState(final ControlState state) {
    return memberLeftBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getHeightForState(final ControlState state) {
    return height.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getLeftPaddingWhenHasState(final ControlState state) {
    return memberLeftPadding.getValueWhenHasState(state);
  }

  @Override
  public final IColor getRightBorderColorWhenHasState(final ControlState state) {
    return memberRightBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getRightBorderThicknessWhenHasState(final ControlState state) {
    return memberRightBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getRightPaddingWhenHasState(final ControlState state) {
    return memberRightPadding.getValueWhenHasState(state);
  }

  @Override
  public final IColor getTopBorderColorWhenHasState(final ControlState state) {
    return memberTopBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getTopBorderThicknessWhenHasState(final ControlState state) {
    return memberTopBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getTopPaddingWhenHasState(final ControlState state) {
    return memberTopPadding.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getWidthForState(final ControlState state) {
    return width.getValueWhenHasState(state);
  }

  @Override
  public final void removeCustomBackgrounds() {
    memberBackground.setUndefined();
  }

  @Override
  public final void removeCustomBorderColors() {
    memberBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomBorderThicknesses() {
    removeCustomLeftBorderColors();
    removeCustomRightBorderColors();
    removeCustomTopBorderColors();
    removeCustomBottomBorderColors();
  }

  @Override
  public final void removeCustomBottomBorderColors() {
    memberBottomBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomBottomBorderThicknesses() {
    memberBottomBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomBottomPaddings() {
    memberBottomPadding.setUndefined();
  }

  @Override
  public final void removeCustomCornerRadiuses() {
    memberCornerRadius.setUndefined();
  }

  @Override
  public final void removeCustomCornerShadows() {
    cornerShadows.setUndefined();
  }

  @Override
  public final void removeCustomHeights() {
    height.setUndefined();
  }

  @Override
  public final void removeCustomLeftBorderColors() {
    memberLeftBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomLeftBorderThicknesses() {
    memberLeftBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomLeftPaddings() {
    memberLeftPadding.setUndefined();
  }

  @Override
  public final void removeCustomPaddings() {
    removeCustomLeftPaddings();
    removeCustomRightPaddings();
    removeCustomTopPaddings();
    removeCustomBottomPaddings();
  }

  @Override
  public final void removeCustomRightBorderColors() {
    memberRightBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomRightBorderThicknesses() {
    memberRightBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomRightPaddings() {
    memberRightPadding.setUndefined();
  }

  @Override
  public final void removeCustomTopBorderColors() {
    memberTopBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomTopBorderThicknesses() {
    memberTopBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomTopPaddings() {
    memberTopPadding.setUndefined();
  }

  @Override
  public final void removeCustomWidths() {
    width.setUndefined();
  }

  @Override
  public final S forStateSetBackgroundColor(final ControlState state, final IColor backgroundColor) {
    return forStateSetBackground(state, Background.withColor(backgroundColor));
  }

  @Override
  public final S forStateSetBackgroundColorGradient(
    final ControlState state,
    final IColorGradient backgroundColorGradient) {
    return forStateSetBackground(state, Background.withColorGradient(backgroundColorGradient));
  }

  @Override
  public final S forStateSetBackground(ControlState state, IBackground background) {
    memberBackground.setValueForState(state, background);

    return asConcrete();
  }

  @Override
  public final S forStateSetBackgroundImage(
    final ControlState state,
    final IImage backgroundImage,
    final ImageApplication imageApplication) {
    return forStateSetBackground(
      state,
      Background.withImageAndImageApplication(backgroundImage, imageApplication));
  }

  @Override
  public final S forStateSetBorderColor(final ControlState state, final IColor borderColor) {
    memberBorderColor.setValueForState(state, borderColor);

    return asConcrete();
  }

  @Override
  public final S forStateSetBorderThickness(final ControlState state, final int borderThickness) {
    memberBorderThickness.setValueForState(state, borderThickness);

    return asConcrete();
  }

  @Override
  public final S forStateSetBottomBorderColor(final ControlState state, final IColor bottomBorderColor) {
    memberBottomBorderColor.setValueForState(state, bottomBorderColor);

    return asConcrete();
  }

  @Override
  public final S forStateSetBottomBorderThickness(final ControlState state, final int bottomBorderThickness) {
    Validator.assertThat(bottomBorderThickness).thatIsNamed("bottom border thickness").isNotNegative();

    memberBottomBorderThickness.setValueForState(state, bottomBorderThickness);

    return asConcrete();
  }

  @Override
  public final S forStateSetBottomPadding(final ControlState state, final int bottomPadding) {
    Validator.assertThat(bottomPadding).thatIsNamed("bottom padding").isNotNegative();

    memberBottomPadding.setValueForState(state, AbsoluteOrRelativeInt.withIntValue(bottomPadding));

    return asConcrete();
  }

  @Override
  public final S forStateSetCornerRadius(final ControlState state, final int cornerRadius) {
    Validator.assertThat(cornerRadius).thatIsNamed("corner radius").isNotNegative();

    memberCornerRadius.setValueForState(state, cornerRadius);

    return asConcrete();
  }

  @Override
  public final S setCornerShadowForState(
    final ControlState state,
    final ICornerShadow cornerShadow,
    final ICornerShadow... cornerShadows) {
    final var allCornerShadows = ContainerView.forElementAndArray(cornerShadow, cornerShadows);

    return setCornerShadowsForState(state, allCornerShadows);
  }

  @Override
  public final S setCornerShadowsForState(final ControlState state,
    final IContainer<? extends ICornerShadow> cornerShadows) {
    this.cornerShadows.setValueForState(state, cornerShadows.to(CornerShadow::fromCornerShadow));

    return asConcrete();
  }

  @Override
  public final S setHeightForState(final ControlState state, final int height) {
    setHeightForState(state, AbsoluteOrRelativeInt.withIntValue(height));

    return asConcrete();
  }

  @Override
  public final S setHeightInPercentOfViewAreaForState(
    final ControlState state,
    final double heightInPercentOfViewAreaHeight) {
    setHeightForState(state, AbsoluteOrRelativeInt.withPercentage(heightInPercentOfViewAreaHeight));

    return asConcrete();
  }

  @Override
  public final S forStateSetLeftBorderColor(final ControlState state, final IColor leftBorderColor) {
    memberLeftBorderColor.setValueForState(state, leftBorderColor);

    return asConcrete();
  }

  @Override
  public final S forStateSetLeftBorderThickness(final ControlState state, final int leftBorderThickness) {
    Validator.assertThat(leftBorderThickness).thatIsNamed("left border thickness").isNotNegative();

    memberLeftBorderThickness.setValueForState(state, leftBorderThickness);

    return asConcrete();
  }

  @Override
  public final S forStateSetLeftPadding(final ControlState state, final int leftPadding) {
    Validator.assertThat(leftPadding).thatIsNamed("left padding").isNotNegative();

    memberLeftPadding.setValueForState(state, AbsoluteOrRelativeInt.withIntValue(leftPadding));

    return asConcrete();
  }

  @Override
  public final S forStateSetPadding(final ControlState state, final int padding) {
    memberPadding.setValueForState(state, AbsoluteOrRelativeInt.withIntValue(padding));

    return asConcrete();
  }

  @Override
  public final S forStateSetRightBorderColor(final ControlState state, final IColor rightBorderColor) {
    memberRightBorderColor.setValueForState(state, rightBorderColor);

    return asConcrete();
  }

  @Override
  public final S forStateSetRightBorderThickness(final ControlState state, final int rightBorderThickness) {
    Validator.assertThat(rightBorderThickness).thatIsNamed("right border thickness").isNotNegative();

    memberRightBorderThickness.setValueForState(state, rightBorderThickness);

    return asConcrete();
  }

  @Override
  public final S forStateSetRightPadding(final ControlState state, final int rightPadding) {
    Validator.assertThat(rightPadding).thatIsNamed("right padding").isNotNegative();

    memberRightPadding.setValueForState(state, AbsoluteOrRelativeInt.withIntValue(rightPadding));

    return asConcrete();
  }

  @Override
  public final S forStateSetTopBorderColor(final ControlState state, final IColor topBorderColor) {
    memberTopBorderColor.setValueForState(state, topBorderColor);

    return asConcrete();
  }

  @Override
  public final S forStateSetTopBorderThickness(final ControlState state, final int topBorderThickness) {
    Validator.assertThat(topBorderThickness).thatIsNamed("top border thickness").isNotNegative();

    memberTopBorderThickness.setValueForState(state, topBorderThickness);

    return asConcrete();
  }

  @Override
  public final S forStateSetTopPadding(final ControlState state, final int topPadding) {
    Validator.assertThat(topPadding).thatIsNamed("top padding").isNotNegative();

    memberTopPadding.setValueForState(state, AbsoluteOrRelativeInt.withIntValue(topPadding));

    return asConcrete();
  }

  @Override
  public final S setWidthForState(final ControlState state, final int width) {
    setWidthForState(state, AbsoluteOrRelativeInt.withIntValue(width));

    return asConcrete();
  }

  @Override
  public final S setWidthInPercentOfViewAreaWidthForState(
    final ControlState state,
    final double widthInPercentOfViewAreaWidth) {
    setWidthForState(state, AbsoluteOrRelativeInt.withPercentage(widthInPercentOfViewAreaWidth));

    return asConcrete();
  }

  private void setBottomPaddingForState(final ControlState state, final IAbsoluteOrRelativeInt bottomPadding) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(bottomPadding);

    memberBottomPadding.setValueForState(state, bottomPadding);
  }

  private void setHeightForState(final ControlState state, final IAbsoluteOrRelativeInt height) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(height);

    this.height.setValueForState(state, height);
  }

  private void setLeftPaddingForState(final ControlState state, final IAbsoluteOrRelativeInt leftPadding) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(leftPadding);

    memberLeftPadding.setValueForState(state, leftPadding);
  }

  private void setRightPaddingForState(final ControlState state, final IAbsoluteOrRelativeInt rightPadding) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(rightPadding);

    memberRightPadding.setValueForState(state, rightPadding);
  }

  private void setTopPaddingForState(final ControlState state, final IAbsoluteOrRelativeInt topPadding) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(topPadding);

    memberTopPadding.setValueForState(state, topPadding);
  }

  private void setWidthForState(final ControlState state, final IAbsoluteOrRelativeInt width) {
    AbsoluteOrRelativeIntValidator.assertIsPositive(width);

    this.width.setValueForState(state, width);
  }
}
