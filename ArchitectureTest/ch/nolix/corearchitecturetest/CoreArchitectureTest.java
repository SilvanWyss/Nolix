package ch.nolix.corearchitecturetest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

final class CoreArchitectureTest {

  private static final JavaClasses TEST_UNIT = new ClassFileImporter().importPackages("ch.nolix.core..");

  @Test
  @Disabled
  void testCase_cycles() {

    //setup
    final var rule = SlicesRuleDefinition.slices().matching("ch.nolix.core.(*)..").should().beFreeOfCycles();

    //execution & verification
    rule.check(TEST_UNIT);
  }

  @Test
  void testCase_dependencies() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage(
        "ch.nolix.core..",
        "ch.nolix.coreapi..",
        "io.netty..",
        "java..",
        "javax..");

    //execution & verification
    rule.check(TEST_UNIT);
  }
}
