package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCliffG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºH;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºS;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºIS;

public class NºCliffGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºH.key(), NºS.key(), NºIS.key())
      .setOutputMetrics(NºCliffG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure H = context.getMeasure(NºH.key());
    Measure S = context.getMeasure(NºS.key());
    Measure IS = context.getMeasure(NºIS.key());
    
    int CliffordGates = H.getIntValue() + S.getIntValue() + IS.getIntValue();

    context.addMeasure(NºCliffG.key(), CliffordGates);
  }
}
