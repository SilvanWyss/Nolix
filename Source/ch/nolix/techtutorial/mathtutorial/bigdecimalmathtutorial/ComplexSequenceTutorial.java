package ch.nolix.techtutorial.mathtutorial.bigdecimalmathtutorial;

import ch.nolix.core.errorcontrol.logger.GlobalLogger;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IIntTaker;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;

public final class ComplexSequenceTutorial {

  public static void main(String[] args) {

    final var complexSequence = new ComplexSequenceDefinedBy1Predecessor(
        new ComplexNumber(0.0, 0.0),
        p -> p.getPower2().getSum(new ComplexNumber(0.0, 1.0)));

    final IIntTaker printFunction = (int i) -> GlobalLogger
        .logInfo("a(" + i + ") = " + complexSequence.getValueAt1BasedIndex(i));

    printFunction.run(1);
    printFunction.run(2);
    printFunction.run(3);
    printFunction.run(4);
    printFunction.run(5);
    printFunction.run(10);
    printFunction.run(100);
    printFunction.run(1000);
  }

  private ComplexSequenceTutorial() {
  }
}
