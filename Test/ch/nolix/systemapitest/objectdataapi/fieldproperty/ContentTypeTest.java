package ch.nolix.systemapitest.objectdataapi.fieldproperty;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.cardinality.Cardinality;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

final class ContentTypeTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getFieldTypesAndTheirCardinality() {
    return //
    ImmutableList.withElement(
      Arguments.of(FieldType.VALUE_FIELD, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_VALUE_FIELD, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_VALUE_FIELD, Cardinality.TO_MANY),
      Arguments.of(FieldType.REFERENCE, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_REFERENCE, Cardinality.TO_MANY),
      Arguments.of(FieldType.BACK_REFERENCE, Cardinality.TO_ONE),
      Arguments.of(FieldType.OPTIONAL_BACK_REFERENCE, Cardinality.TO_ONE_OR_NONE),
      Arguments.of(FieldType.MULTI_BACK_REFERENCE, Cardinality.TO_MANY));
  }

  @ParameterizedTest
  @MethodSource("getFieldTypesAndTheirCardinality")
  void testCase_getCardinality(final FieldType testUnit, final Cardinality expectedCardinality) {

    //execution
    final var result = testUnit.getCardinality();

    //verification
    expect(result).is(expectedCardinality);
  }
}
