//package declaration
package ch.nolix.coretest.mathtest.stochastictest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.math.stochastic.ARModel;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ARModelTest extends StandardTest {

  //method
  @Test
  void testCase_constructor_1() {

    //execution
    final double[] inputValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    final var lARModel = new ARModel(1, inputValues);

    //verification
    expect(lARModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(11.0);
    expect(lARModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(12.0);
    expect(lARModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(13.0);
    expect(lARModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(14.0);
  }

  //method
  @Test
  void testCase_constructor_2() {

    //execution
    final double[] inputValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    final var lARModel = new ARModel(2, inputValues);

    //verification
    expect(lARModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(11.0);
    expect(lARModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(12.0);
    expect(lARModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(13.0);
    expect(lARModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(14.0);
  }

  //method
  @Test
  void testCase_constructor_3() {

    //execution
    final double[] inputValues = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
    final var lARModel = new ARModel(2, inputValues);

    //verification
    expect(lARModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lARModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(2.0);
  }

  //method
  @Test
  void testCase_constructor_4() {

    //execution
    final double[] inputValues = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
    final var lARModel = new ARModel(3, inputValues);

    //verification
    expect(lARModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lARModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(2.0);
  }

  //method
  @Test
  void testCase_constructor_5() {

    //execution
    final double[] inputValues = { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3 };
    final var lARModel = new ARModel(3, inputValues);

    //verification
    expect(lARModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lARModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(3.0);
    expect(lARModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(5)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lARModel.getForecast(6)).withMaxDeviation(0.01).isEqualTo(3.0);
  }

  //method
  @Test
  void testCase_constructor_6() {

    //exection
    final double[] inputValues = { 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3 };
    final var lARModel = new ARModel(4, inputValues);

    //verification
    expect(lARModel.getForecast(1)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(2)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lARModel.getForecast(3)).withMaxDeviation(0.01).isEqualTo(3.0);
    expect(lARModel.getForecast(4)).withMaxDeviation(0.01).isEqualTo(1.0);
    expect(lARModel.getForecast(5)).withMaxDeviation(0.01).isEqualTo(2.0);
    expect(lARModel.getForecast(6)).withMaxDeviation(0.01).isEqualTo(3.0);
  }
}
