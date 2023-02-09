package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCPG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCNOT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCY;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCZ;

public class NºCPGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºCNOT.key(), NºCY.key(), NºCZ.key())
      .setOutputMetrics(NºCPG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure CNOT = context.getMeasure(NºCNOT.key());
    Measure CY = context.getMeasure(NºCY.key());
    Measure CZ = context.getMeasure(NºCZ.key());
    
    int CPGates = CNOT.getIntValue() + CY.getIntValue() + CZ.getIntValue();

    context.addMeasure(NºCPG.key(), CPGates);
  }
}
