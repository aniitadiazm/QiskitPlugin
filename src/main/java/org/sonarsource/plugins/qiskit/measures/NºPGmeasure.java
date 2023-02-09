package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºPG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºNOT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºY;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºZ;

public class NºPGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºNOT.key(), NºY.key(), NºZ.key())
      .setOutputMetrics(NºPG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure NOT = context.getMeasure(NºNOT.key());
    Measure Y = context.getMeasure(NºY.key());
    Measure Z = context.getMeasure(NºZ.key());
    
    int PauliGates = NOT.getIntValue() + Y.getIntValue() + Z.getIntValue();

    context.addMeasure(NºPG.key(), PauliGates);
  }
}
