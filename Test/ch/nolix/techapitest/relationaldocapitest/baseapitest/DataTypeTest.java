//package declaration
package ch.nolix.techapitest.relationaldocapitest.baseapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//class
final class DataTypeTest extends StandardTest {

  //method
  @Test
  void testCase_getDataTypeClass_whenIsInteger8Byte() {

    //setup
    final var testUnit = DataType.INTEGER_8BYTE;

    //execution
    final var result = testUnit.getDataTypeClass();

    //verification
    expect(result).is(Long.class);
  }

  //method
  @Test
  void testCase_getDataTypeClass_whenIsFloatingPointNumber8Byte() {

    //setup
    final var testUnit = DataType.FLOATING_POINT_NUMBER_8BYTE;

    //execution
    final var result = testUnit.getDataTypeClass();

    //verification
    expect(result).is(Double.class);
  }

  //method
  @Test
  void testCase_getDataTypeClass_whenIsBoolean() {

    //setup
    final var testUnit = DataType.BOOLEAN;

    //execution
    final var result = testUnit.getDataTypeClass();

    //verification
    expect(result).is(Boolean.class);
  }

  //method
  @Test
  void testCase_getDataTypeClass_whenIsText() {

    //setup
    final var testUnit = DataType.TEXT;

    //execution
    final var result = testUnit.getDataTypeClass();

    //verification
    expect(result).is(String.class);
  }

  //method
  @Test
  void testCase_getDataTypeClass_whenIsImage() {

    //setup
    final var testUnit = DataType.IMAGE;

    //execution
    final var result = testUnit.getDataTypeClass();

    //verification
    expect(result).is(IImage.class);
  }
}
