package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºMCG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.Nº2CG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.Nº3CG;

public class NºMCGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(Nº2CG.key(), Nº3CG.key())
      .setOutputMetrics(NºMCG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure C2G = context.getMeasure(Nº2CG.key());
    Measure C3G = context.getMeasure(Nº3CG.key());

    int MCGates = C2G.getIntValue() + C3G.getIntValue();

    context.addMeasure(NºMCG.key(), MCGates);
  }
}
