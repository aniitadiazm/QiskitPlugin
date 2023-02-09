package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.PorcMCG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºTG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºMCG;

public class PorcMCGmeasure implements MeasureComputer{

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setInputMetrics(NºTG.key(), NºMCG.key())
            .setOutputMetrics(PorcMCG.key())
            .build();
    }
  
    public void compute(MeasureComputerContext context) {
        Measure TG = context.getMeasure(NºTG.key());
        Measure MCG = context.getMeasure(NºMCG.key());
        
        double porcMCGates = 0;
        if(TG != null && MCG != null){
            porcMCGates = ((double)MCG.getIntValue() / (double)TG.getIntValue()) * 100;
        }

        context.addMeasure(PorcMCG.key(), porcMCGates);
    }
}
