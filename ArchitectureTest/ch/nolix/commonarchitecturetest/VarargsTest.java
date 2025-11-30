package ch.nolix.commonarchitecturetest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class VarargsTest {
  @Disabled
  @Test
  void testCase_methodsWithVarargsParametersDoNotHaveOtherParameters() {
    //setup
    final var rule = //
    ArchRuleDefinition
      .methods()
      .should(new ArchCondition<JavaMethod>("Methods with varargs parameters do not have other parameters.") {
        @Override
        public void check(final JavaMethod item, final ConditionEvents events) {
          if (item.getParameters().size() > 1 && item.reflect().isVarArgs()) {
            final var message = "The method" + item.getFullName() + " has varargs parameters and other parameters.";

            events.add(new SimpleConditionEvent(item, false, message));
          }
        }
      });

    final var testUnit = new ClassFileImporter().importPackages("ch.nolix...");

    //execution & verification
    rule.check(testUnit);
  }
}
