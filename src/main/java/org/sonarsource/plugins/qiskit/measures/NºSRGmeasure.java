package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºSRG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºRX;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºRY;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºRZ;

public class NºSRGmeasure implements MeasureComputer {

  @Override
  public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
    return def.newDefinitionBuilder()
      .setInputMetrics(NºRX.key(), NºRY.key(), NºRZ.key())
      .setOutputMetrics(NºSRG.key())
      .build();
  }

  public void compute(MeasureComputerContext context) {
    Measure RX = context.getMeasure(NºRX.key());
    Measure RY = context.getMeasure(NºRY.key());
    Measure RZ = context.getMeasure(NºRZ.key());
    
    int SRGates = RX.getIntValue() + RY.getIntValue() + RZ.getIntValue();

    context.addMeasure(NºSRG.key(), SRGates);
  }
}