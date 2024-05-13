//package declaration
package ch.nolix.systemapi.timeapi.timestructureapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum Month {
  JANUARY,
  FEBRUARY,
  MARCH,
  APRIL,
  MAY,
  JUNE,
  JULY,
  AUGUST,
  SEPTEMBER,
  OCTOBER,
  NOVEMBER,
  DECEMBER;

  //static method
  public static Month fromJavaMonth(final java.time.Month month) { //NOSONAR: This method is uniform.
    return switch (month) {
      case JANUARY ->
        JANUARY;
      case FEBRUARY ->
        FEBRUARY;
      case MARCH ->
        MARCH;
      case APRIL ->
        APRIL;
      case MAY ->
        MAY;
      case JUNE ->
        JUNE;
      case JULY ->
        JULY;
      case AUGUST ->
        AUGUST;
      case SEPTEMBER ->
        SEPTEMBER;
      case OCTOBER ->
        OCTOBER;
      case NOVEMBER ->
        NOVEMBER;
      case DECEMBER ->
        DECEMBER;
      default ->
        throw new IllegalArgumentException("The given month '" + month + "' is not valid.");
    };
  }

  //static method
  public Month fromSpecification(final INode<?> specification) {
    return Month.valueOf(specification.getSingleChildNodeHeader());
  }
}
