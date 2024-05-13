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
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

//class
final class FieldTypeTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getFieldTypesAndTheirCardinality() {
    return //
    ImmutableList.withElement(
      Arguments.of(ContentType.VALUE, Cardinality.TO_ONE),
      Arguments.of(ContentType.OPTIONAL_VALUE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(ContentType.MULTI_VALUE, Cardinality.TO_MANY),
      Arguments.of(ContentType.REFERENCE, Cardinality.TO_ONE),
      Arguments.of(ContentType.OPTIONAL_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(ContentType.MULTI_REFERENCE, Cardinality.TO_MANY),
      Arguments.of(ContentType.BACK_REFERENCE, Cardinality.TO_ONE),
      Arguments.of(ContentType.OPTIONAL_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(ContentType.MULTI_BACK_REFERENCE, Cardinality.TO_MANY));
  }

  //method
  @ParameterizedTest
  @MethodSource("getFieldTypesAndTheirCardinality")
  void testCase(final ContentType testUnit, final Cardinality expectedCardinality) {

    //execution
    final var result = testUnit.getCardinality();

    //verification
    expect(result).is(expectedCardinality);
  }
}
