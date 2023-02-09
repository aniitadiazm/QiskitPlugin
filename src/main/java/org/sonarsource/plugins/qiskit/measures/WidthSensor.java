package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import java.io.IOException;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.WIDTH;

public class WidthSensor implements Sensor {

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Calcular el número de cúbits creados");
    }

    @Override
    public void execute(SensorContext context) {
        FileSystem fs = context.fileSystem();
        // only "main" files, but not "tests"
        Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
        for (InputFile file : files) {
            try {
                context.<Integer>newMeasure()
                    .forMetric(WIDTH)
                    .on(file)
                    .withValue(calculate(file, context))
                    .save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int calculate(InputFile f, SensorContext context) throws IOException {
        int totalcubits = 0, intcubits = 0;
        String[] parts, parts2;
        String cubits, part1, build_cubits;
        if (f.isFile()) {
            try {
                String content = f.contents();
                String[] lines = content.split("\\R");
                for (String line : lines) {
                    if (line.contains("QuantumRegister(") && !line.contains("QuantumCircuit(")) {
                        build_cubits = line.replace(" ", "");
                        parts = build_cubits.split("\\(");
                        part1 = parts[1];
                        if(part1.contains(",")) {
                            parts = part1.split("\\,");
                            cubits = parts[0];
                        }else{
                            parts = part1.split("\\)");
                            cubits = parts[0];
                        }
                        intcubits = Integer.valueOf(cubits);
                        totalcubits = totalcubits + intcubits;
                    }
                    else if (line.contains("QuantumRegister(") && line.contains("QuantumCircuit(")) {
                        build_cubits = line.replace(" ", "");
                        parts = build_cubits.split("\\,");
                        for(int i=0; i<parts.length; i++){
                            if(parts[i].contains("QuantumRegister")){
                                parts2 = parts[i].split("\\(");
                                if(parts2.length == 3){
                                    part1 = parts2[2];
                                } else {
                                    part1 = parts2[1];
                                }
                                if(part1.contains(")")){
                                    parts2 = part1.split("\\)");
                                    cubits = parts2[0];
                                } else {
                                    cubits = part1;
                                }
                                intcubits = Integer.valueOf(cubits);
                                totalcubits = totalcubits + intcubits;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return totalcubits;
    }
}
