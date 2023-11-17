//package declaration
package ch.nolix.core.math.stochastic;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.algebra.Matrix;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2016-08-01
 */
public final class ARModel extends StatisticalModel {

  //attribute
  private final double constant;

  //multi-attribute
  private final double[] pCoefficients;

  //constructor
  public ARModel(final int pOrder, final double[] inputValues) {

    //Calls constructor of the base class.
    super(pOrder, inputValues);

    //Creates factor1 matrix.
    final var factor1Matrix = new Matrix(inputValues.length - pOrder, pOrder + 1);
    for (var i = 1; i <= factor1Matrix.getRowCount(); i++) {

      for (var j = 1; j < factor1Matrix.getColumnCount(); j++) {
        factor1Matrix.setValue(i, j, inputValues[i + factor1Matrix.getColumnCount() - j - 2]);
      }

      factor1Matrix.setValue(i, factor1Matrix.getColumnCount(), 1);
    }

    //Creates product matrix.
    final var productMatrix = new Matrix(inputValues.length - pOrder, 1);
    for (var i = pOrder; i < inputValues.length; i++) {
      productMatrix.setValue(i - pOrder + 1, 1, inputValues[i]);
    }

    //Calculates factor2 matrix.
    final var factor2Matrix = factor1Matrix.getMinimalFactorMatrix(productMatrix);

    //Sets p-coefficients.
    pCoefficients = new double[pOrder];
    for (var i = 0; i < pOrder; i++) {
      pCoefficients[i] = factor2Matrix.getValue(i + 1, 1);
    }

    //Sets constant.
    constant = factor2Matrix.getValue(factor2Matrix.getSize(), 1);
  }

  //method
  /**
   * @return the constant of this AR model
   */
  public double getConstant() {
    return constant;
  }

  //method
  public double getPCoefficient(final int index) {

    GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isBetween(1, getPOrder());

    return pCoefficients[index - 1];
  }

  //method
  /**
   * @return the p-order of this AR model
   */
  public int getPOrder() {
    return pCoefficients.length;
  }

  @Override
  protected double calculateNextValue() {

    var nextValue = constant;
    for (var i = 0; i < getPOrder(); i++) {
      nextValue += pCoefficients[i] * getValueFromBack(i + 1);
    }

    return nextValue;
  }
}
