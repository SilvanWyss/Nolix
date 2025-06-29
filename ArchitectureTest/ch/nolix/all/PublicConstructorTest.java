package ch.nolix.all;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class PublicConstructorTest {

  //TODO: Enable test
  @Disabled
  @Test
  void testCase_publicConstructorsAreDefaultConstructors() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .constructors()
      .that()
      .arePublic()
      .should(new ArchCondition<JavaConstructor>("public consructors are default constructors.") {

        @Override
        public void check(final JavaConstructor item, final ConditionEvents events) {

          final var isDefaultConstructor = item.getParameters().isEmpty();
          final var message = item.getFullName() + " is not a default constructor";

          events.add(new SimpleConditionEvent(item, isDefaultConstructor, message));
        }
      });
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix...");

    //execution & verification
    rule.check(testUnit);
  }
}
