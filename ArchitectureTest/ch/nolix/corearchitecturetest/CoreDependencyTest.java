package ch.nolix.corearchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class CoreDependencyTest {

  @Test
  void testCase_chnolixcore_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.core..")
      .and()
      .haveNameNotMatching(".*Test$")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage("ch.nolix.core..", "ch.nolix.coreapi..", "io.netty..", "java..", "javax..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.core..");

    //execution & verification
    rule.check(testUnit);
  }
}
