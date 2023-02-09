package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.PorcSCG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºSCG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºTG;

public class PorcSCGmeasure implements MeasureComputer{

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setInputMetrics(NºSCG.key(), NºTG.key())
            .setOutputMetrics(PorcSCG.key())
            .build();
    }
  
    public void compute(MeasureComputerContext context) {
        Measure SCG = context.getMeasure(NºSCG.key());
        Measure TG = context.getMeasure(NºTG.key()); 
        
        double porcSCGates = 0;
        if(SCG != null && TG != null){
            porcSCGates = ((double)SCG.getIntValue() / (double)TG.getIntValue()) * 100;
        }
        context.addMeasure(PorcSCG.key(), porcSCGates);
    }
}
