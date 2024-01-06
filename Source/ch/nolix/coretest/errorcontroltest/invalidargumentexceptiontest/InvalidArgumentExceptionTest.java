//package declaration
package ch.nolix.coretest.errorcontroltest.invalidargumentexceptiontest;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class InvalidArgumentExceptionTest extends Test {

  //method
  @TestCase
  public void testCase_forArgument_whenArgumentIsNull() {

    //execution
    final var result = InvalidArgumentException.forArgument(null);

    //verification
    expect(result.getArgumentName()).isEqualTo("argument");
    expect(result.getStoredArgument()).isNull();
    expect(result.getErrorPredicate()).isEqualTo("is not valid");
    expect(result.getMessage()).isEqualTo("The given argument is not valid.");
  }

  //method
  @TestCase
  public void testCase_forArgument_whenArgumentIsANode() {

    //setup
    final var node = Node.fromString("Parking(Slot(Id(A)), Slot(Id(B)))");

    //execution
    final var result = InvalidArgumentException.forArgument(node);

    //verification
    expect(result.getArgumentName()).isEqualTo("Node");
    expect(result.getStoredArgument()).is(node);
    expect(result.getErrorPredicate()).isEqualTo("is not valid");
    expect(result.getMessage()).isEqualTo("The given Node 'Parking(Slot(Id(A)), Slot(Id(B)))' is not valid.");
  }

  //method
  @TestCase
  public void testCase_forArgumentAndErrorPredicate() {

    //setup
    final var amount = BigDecimal.valueOf(10.5);

    //execution
    final var result = InvalidArgumentException.forArgumentAndErrorPredicate(amount, "is not a whole number");

    //verification
    expect(result.getArgumentName()).isEqualTo("BigDecimal");
    expect(result.getStoredArgument()).is(amount);
    expect(result.getErrorPredicate()).isEqualTo("is not a whole number");
    expect(result.getMessage()).isEqualTo("The given BigDecimal '10.5' is not a whole number.");
  }

  //method
  @TestCase
  public void testCase_forArgumentNameAndArgumentAndErrorPredicate() {

    //setup
    final var amount = BigDecimal.valueOf(10.5);

    //execution
    final var result = InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
      "amount",
      amount,
      "is not a whole number");

    //verification
    expect(result.getArgumentName()).isEqualTo("amount");
    expect(result.getStoredArgument()).is(amount);
    expect(result.getErrorPredicate()).isEqualTo("is not a whole number");
    expect(result.getMessage()).isEqualTo("The given amount '10.5' is not a whole number.");
  }
}
