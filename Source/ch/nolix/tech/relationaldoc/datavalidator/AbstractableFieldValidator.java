//package declaration
package ch.nolix.tech.relationaldoc.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableFieldEvaluator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;

//class
public final class AbstractableFieldValidator {

  //constant
  private static final AbstractableFieldEvaluator ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldEvaluator();

  //method
  public void assertCanBeSetAsAbstract(final IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetAsAbstract(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set as abstract");
    }
  }

  //method
  public void assertCanBeSetAsConcrete(IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetAsConcrete(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set as concrete");
    }
  }

  //method
  public void assertCanBeSetForReferences(final IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetForReferences(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set for references");
    }
  }

  //method
  public void assertCanBeSetForValues(final IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetForValues(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set for values");
    }
  }

  //method
  public void assertCanSetCardinality(final IAbstractableField abstractableField, final Cardinality cardinality) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canSetCardinality(abstractableField, cardinality)) {
      throw InvalidArgumentException.forArgument(cardinality);
    }
  }

  //method
  public void assertCanSetName(final IAbstractableField abstractableField, final String name) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canSetName(abstractableField, name)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.NAME, name);
    }
  }

  //method
  public void assertDoesNotInheritFromAnotherField(final IAbstractableField abstractableField) {
    if (abstractableField.inheritsFromBaseField()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "inherits from another field");
    }
  }
}
