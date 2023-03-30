package org.sonarsource.plugins.qiskit;

import org.sonar.api.Plugin;

import org.sonarsource.plugins.qiskit.measures.ComputeQiskit;
import org.sonarsource.plugins.qiskit.measures.Depth;
import org.sonarsource.plugins.qiskit.measures.HighComplexityGates;
import org.sonarsource.plugins.qiskit.measures.MediumComplexityGates;
import org.sonarsource.plugins.qiskit.measures.LowComplexityGates;
import org.sonarsource.plugins.qiskit.measures.InitResetOperations;
import org.sonarsource.plugins.qiskit.measures.MeasureOperations;
import org.sonarsource.plugins.qiskit.measures.QiskitMetrics;
import org.sonarsource.plugins.qiskit.measures.Width;
import org.sonarsource.plugins.qiskit.measures.AuxiliaryQubits;
import org.sonarsource.plugins.qiskit.measures.ConditionalInstructions;
import org.sonarsource.plugins.qiskit.measures.QuantumCyclomaticComplexity;
import org.sonarsource.plugins.qiskit.settings.PythonLanguageProperties;
import org.sonarsource.plugins.qiskit.settings.QiskitProperties;

/**
 * This class is the entry point for all extensions. It is referenced in pom.xml.
 */
public class QiskitPlugin implements Plugin {

  @Override
  public void define(Context context) {

    // tutorial on languages
    // https://docs.sonarqube.org/9.4/extend/new-languages/
    //context.addExtensions(PythonLanguage.class, FooQualityProfile.class);
    context.addExtensions(PythonLanguageProperties.getProperties());

    // tutorial on measures
    context
      .addExtensions(QiskitMetrics.class, ComputeQiskit.class, Width.class, Depth.class, LowComplexityGates.class,
      MediumComplexityGates.class, HighComplexityGates.class, ConditionalInstructions.class, QuantumCyclomaticComplexity.class,
      MeasureOperations.class, InitResetOperations.class, AuxiliaryQubits.class
      );

    // tutorial on settings
    context
      .addExtensions(QiskitProperties.getProperties());
  }
}
