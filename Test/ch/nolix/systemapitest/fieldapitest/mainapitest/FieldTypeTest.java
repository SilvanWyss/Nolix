//package declaration
package ch.nolix.systemapitest.fieldapitest.mainapitest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//class
final class FieldTypeTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getFieldTypesAndTheirCardinality() {
    return //
    ImmutableList.withElement(
      Arguments.of(FieldType.VALUE, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_VALUE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_VALUE, Cardinality.TO_MANY),
      Arguments.of(FieldType.REFERENCE, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_REFERENCE, Cardinality.TO_MANY),
      Arguments.of(FieldType.BACK_REFERENCE, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_BACK_REFERENCE, Cardinality.TO_MANY));
  }

  //method
  @ParameterizedTest
  @MethodSource("getFieldTypesAndTheirCardinality")
  void testCase(final FieldType testUnit, final Cardinality expectedCardinality) {

    //execution
    final var result = testUnit.getCardinality();

    //verification
    expect(result).is(expectedCardinality);
  }
}
