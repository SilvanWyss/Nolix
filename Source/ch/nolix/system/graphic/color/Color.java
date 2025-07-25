package ch.nolix.system.graphic.color;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.pair.IPair;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

/**
 * A {@link Color} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class Color extends AbstractElement implements IColor {

  public static final int DEFAULT_ALPHA_VALUE = 255;

  public static final long MIN_COLOR_LONG = 0;

  public static final long MAX_COLOR_LONG = 4_294_967_296L;

  public static final short MIN_COLOR_COMPONENT = 0;

  public static final short MAX_COLOR_COMPONENT = 255;

  private static IContainer<IPair<String, IColor>> x11Colors;

  private final short redValue;

  private final short greenValue;

  private final short blueValue;

  private final short alphaValue;

  /**
   * Creates a new {@link Color} with the given redValue, greenValue and
   * blueValue.
   * 
   * @param redValue
   * @param greenValue
   * @param blueValue
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given alphaValue is not a true
   *                                       color component (in [0, 255]).
   */
  private Color(final int redValue, final int greenValue, final int blueValue) {
    this(redValue, greenValue, blueValue, DEFAULT_ALPHA_VALUE);
  }

  /**
   * Creates a new {@link Color} with the given redValue, greenValue, blueValue
   * and alphaValue.
   * 
   * @param redValue
   * @param greenValue
   * @param blueValue
   * @param alphaValue
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given alphaValue is not a true
   *                                       color component (in [0, 255]).
   */
  private Color(final int redValue, final int greenValue, final int blueValue, final int alphaValue) {

    Validator
      .assertThat(redValue)
      .thatIsNamed("red value")
      .isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);

    Validator
      .assertThat(greenValue)
      .thatIsNamed("green value")
      .isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);

    Validator
      .assertThat(blueValue)
      .thatIsNamed("blue value")
      .isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);

    Validator
      .assertThat(blueValue)
      .thatIsNamed("alpha value")
      .isBetween(MIN_COLOR_COMPONENT, MAX_COLOR_COMPONENT);

    this.redValue = (short) redValue;
    this.greenValue = (short) greenValue;
    this.blueValue = (short) blueValue;
    this.alphaValue = (short) alphaValue;
  }

  public static Color createAverageFrom(final IColor color, final IColor... colors) {

    final var allColors = ContainerView.forElementAndArray(color, colors);

    return createAverageFrom(allColors);
  }

  public static Color createAverageFrom(final IContainer<IColor> colors) {

    final var colorCount = colors.getCount();

    var averageRedValue = 0;
    var averageGreenValue = 0;
    var averageBlueValue = 0;
    var averateAlphaValue = 0;

    for (final var c : colors) {
      averageRedValue += c.getRedValue();
      averageGreenValue += c.getGreenValue();
      averageBlueValue += c.getBlueValue();
      averateAlphaValue += c.getAlphaValue();
    }

    return new Color(
      averageRedValue / colorCount,
      averageGreenValue / colorCount,
      averageBlueValue / colorCount,
      averateAlphaValue / colorCount);
  }

  /**
   * @param color
   * @return a new {@link Color} from the given color.
   * @throws NullPointerException if the given color is null.
   */
  public static Color fromColor(final IColor color) {

    if (color instanceof final Color localColor) {
      return localColor;
    }

    return //
    withRedValueAndGreenValueAndBlueValueAndAlphaValue(
      color.getRedValue(),
      color.getGreenValue(),
      color.getBlueValue(),
      color.getAlphaValue());
  }

  /**
   * @param pLong
   * @return a new {@link Color} from the given pLong.
   * @throws UnrepresentingArgumentException if the given pLong does not represent
   *                                         a {@link Color}.
   */
  public static Color fromLong(final long pLong) {

    //Asserts that the given pLong is a true color value.
    Validator.assertThat(pLong).isBetween(MIN_COLOR_LONG, MAX_COLOR_LONG);

    var lLong = pLong;

    var alphaValue = DEFAULT_ALPHA_VALUE;

    //Handles the case that the given pLong specifies an alpha value.
    if (lLong >= 16_777_216) {
      alphaValue = ((int) (lLong % 256));
      lLong /= 256;
    }

    final var blueValue = (int) (lLong % 256);
    lLong /= 256;

    final var greenValue = ((int) (lLong % 256));
    lLong /= 256;

    final var redValue = (int) lLong;

    return new Color(redValue, greenValue, blueValue, alphaValue);
  }

  /**
   * @param specification
   * @return a new {@link Color} from the given specification
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static Color fromSpecification(final INode<?> specification) {
    return Color.fromString(specification.getSingleChildNodeHeader());
  }

  /**
   * @param string
   * @return a new {@link Color} from the given string.
   * @throws UnrepresentingArgumentException if the given string does not
   *                                         represent a {@link Color}.
   */
  public static Color fromString(final String string) {

    final var webColorAndName = getX11Colors()
      .getOptionalStoredFirst(p -> p.getStoredElement1().equals(string));

    //Handles the case that the given string is not a color name.
    if (webColorAndName.isEmpty()) {

      if ((string.length() != 8 || string.length() != 10)
      && !string.substring(0, 2).equals(StringCatalog.HEXADECIMAL_PREFIX)) {
        throw UnrepresentingArgumentException.forArgumentAndType(string, Color.class);
      }

      final var redValue = getColorComponentFrom(string.substring(2, 4));
      final var greenValue = getColorComponentFrom(string.substring(4, 6));
      final var blueValue = getColorComponentFrom(string.substring(6, 8));

      //Handles the case that the given string does not specify an alpha value.
      if (string.length() == 8) {
        return new Color(redValue, greenValue, blueValue);
      }

      //Handles the case that the given string specifies an alpha value.
      final var alphaValue = getColorComponentFrom(string.substring(8, 10));
      return new Color(redValue, greenValue, blueValue, alphaValue);
    }

    //Handles the case that the given value is a color name.
    return (Color) webColorAndName.get().getStoredElement2();
  }

  /**
   * @param redValue
   * @param greenValue
   * @param blueValue
   * @return a new {@link Color} with the given redValue, greenValue and
   *         blueValue.
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   */
  public static Color withRedValueAndGreenValueAndBlueValue(
    final int redValue,
    final int greenValue,
    final int blueValue) {
    return new Color(redValue, greenValue, blueValue);
  }

  /**
   * @param redValue
   * @param greenValue
   * @param blueValue
   * @param alphaValue
   * @return a new {@link Color} with the given redValue, greenValue, blueValue
   *         and alphaValue.
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given greenValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given blueValue is not a true
   *                                       color component (in [0, 255]).
   * @throws ArgumentIsOutOfRangeException if the given alphaValue is not a true
   *                                       color component (in [0, 255]).
   */
  public static Color withRedValueAndGreenValueAndBlueValueAndAlphaValue(
    final int redValue,
    final int greenValue,
    final int blueValue,
    final int alphaValue) {
    return new Color(redValue, greenValue, blueValue, alphaValue);
  }

  /**
   * @param string
   * @return the color component the given string represents.
   * @throws UnrepresentingArgumentException if the given string does not
   *                                         represent a color component.
   */
  private static int getColorComponentFrom(final String string) {

    var value = 0;
    var base = 1;

    //Iterates the given string.
    for (var i = string.length() - 1; i >= 0; i--) {

      final var hexadecimalDigit = string.charAt(i);
      final var characterInt = mapHexadecimalDigitFromStringToInt(hexadecimalDigit, string);

      value += characterInt * base;
      base *= 16;
    }

    return value;
  }

  /**
   * @return the names and values of the X11 colors.
   */
  private static IContainer<IPair<String, IColor>> getX11Colors() {

    if (x11Colors == null) {
      x11Colors = new X11ColorCatalogExtractor().getColorConstantsFromClass(X11ColorCatalog.class);
    }

    return x11Colors;
  }

  /**
   * @param hexadecimalDigit
   * @param string
   * @return the int the given hexadecimalDigit, that is from the given string,
   *         represents.
   * @InvalidArgumentException if the given hexadecimalDigit is not valid.
   */
  private static int mapHexadecimalDigitFromStringToInt( //NOSONAR: This method is uniform.
    char hexadecimalDigit,
    final String string) {

    //Enumerates the given character.
    return switch (hexadecimalDigit) {
      case '0' ->
        0;
      case '1' ->
        1;
      case '2' ->
        2;
      case '3' ->
        3;
      case '4' ->
        4;
      case '5' ->
        5;
      case '6' ->
        6;
      case '7' ->
        7;
      case '8' ->
        8;
      case '9' ->
        9;
      case 'A' ->
        10;
      case 'B' ->
        11;
      case 'C' ->
        12;
      case 'D' ->
        13;
      case 'E' ->
        14;
      case 'F' ->
        15;
      default ->
        throw InvalidArgumentException.forArgument(string);
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getAlphaPercentage() {
    return ((double) getAlphaValue() / Color.MAX_COLOR_COMPONENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAlphaValue() {
    return alphaValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return LinkedList.withElement(Node.withHeader(toHexadecimalString()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getBluePercentage() {
    return ((double) getBlueValue() / Color.MAX_COLOR_COMPONENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getBlueValue() {
    return blueValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColorNameOrHexadecimalString() {

    final var webColorAndName = getX11Colors().getOptionalStoredFirst(wc -> wc.getStoredElement2().equals(this));

    //Handles the case that the current Color has a color name.
    if (webColorAndName.isPresent()) {
      return webColorAndName.get().getStoredElement1();
    }

    return toHexadecimalString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getGreenPercentage() {
    return ((double) getGreenValue() / Color.MAX_COLOR_COMPONENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getGreenValue() {
    return greenValue;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Color getInvertedColor() {
    return new Color(
      MAX_COLOR_COMPONENT - redValue,
      MAX_COLOR_COMPONENT - greenValue,
      MAX_COLOR_COMPONENT - blueValue,
      alphaValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getRedPercentage() {
    return ((double) getRedValue() / Color.MAX_COLOR_COMPONENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getRedValue() {
    return redValue;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasFullAlphaValue() {
    return (alphaValue == MAX_COLOR_COMPONENT);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasFullBlueValue() {
    return (blueValue == MAX_COLOR_COMPONENT);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasFullGreenValue() {
    return (greenValue == MAX_COLOR_COMPONENT);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasFullRedValue() {
    return (redValue == MAX_COLOR_COMPONENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int toAlphaRedGreenBlueInt() {
    return ((getAlphaValue() << 24) | (getRedValue() << 16) | (getGreenValue() << 8) | getBlueValue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toHexadecimalString() {

    var string = StringCatalog.HEXADECIMAL_PREFIX
    + String.format("%02X", redValue)
    + String.format("%02X", greenValue)
    + String.format("%02X", blueValue);

    //Handles the case that the current color does not have a full alpha value.
    if (!hasFullAlphaValue()) {
      string += String.format("%02X", alphaValue);
    }

    return string;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toHexadecimalStringWithAlphaValue() {
    return StringCatalog.HEXADECIMAL_PREFIX
    + String.format("%02X", redValue)
    + String.format("%02X", greenValue)
    + String.format("%02X", blueValue)
    + String.format("%02X", alphaValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long toLong() {

    //Handles the case that the current Color does not have a full alpha value.
    if (!hasFullAlphaValue()) {
      return 16_777_216L * getRedValue()
      + 65536 * getGreenValue()
      + 256 * getBlueValue()
      + getAlphaValue();
    }

    //Handles the case that the current Color has a full alpha value.
    return 65536L * getRedValue()
    + 256 * getGreenValue()
    + getBlueValue();
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public IColor withFloatingPointAlphaValue(final double floatingPointAlphaValue) {

    Validator
      .assertThat(floatingPointAlphaValue)
      .thatIsNamed("floating point number alpha value")
      .isBetween(0.0, 1.0);

    return withRedValueAndGreenValueAndBlueValueAndAlphaValue(
      redValue,
      greenValue,
      blueValue,
      (int) (MAX_COLOR_COMPONENT * floatingPointAlphaValue));
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Color withFullAlphaValue() {
    return new Color(redValue, greenValue, blueValue);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Color withAlphaValue(final int alphaValue) {
    return withRedValueAndGreenValueAndBlueValueAndAlphaValue(redValue, greenValue, blueValue, alphaValue);
  }
}
