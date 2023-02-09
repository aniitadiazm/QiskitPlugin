package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºC3G;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºT;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºIT;

public class NºC3Gmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºT.key(), NºIT.key())
      .setOutputMetrics(NºC3G.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure T = context.getMeasure(NºT.key());
    Measure IT = context.getMeasure(NºIT.key());
    
    int C3Gates = T.getIntValue() + IT.getIntValue();

    context.addMeasure(NºC3G.key(), C3Gates);
  }
}
