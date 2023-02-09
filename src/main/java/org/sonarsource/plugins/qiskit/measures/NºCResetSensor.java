package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import java.io.IOException;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCReset;

public class NºCResetSensor implements Sensor {

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Calcular el número de cúbits con alguna operación Reset");
    }

    @Override
    public void execute(SensorContext context) {
        FileSystem fs = context.fileSystem();
        // only "main" files, but not "tests"
        Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
        for (InputFile file : files) {
            try {
                context.<Integer>newMeasure()
                    .forMetric(NºCReset)
                    .on(file)
                    .withValue(calculate(file, context))
                    .save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int calculate(InputFile f, SensorContext context) throws IOException {
        String linea, ope, part0, part1;
        String[] parts, parts2;
        int cubit, cubits_resets=0;
        int[] resets = new int[100];
        if (f.isFile()) {
            try {
                String content = f.contents();
                String[] lines = content.split("\\R");
                for (String line : lines) {
                    linea = line.replace(" ", "");
                    if (linea.contains(".reset(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        if(part0.contains("[") && part0.contains(",")) {
                            parts = part0.split("\\,");
                            for(int i=0; i<parts.length; i++) {
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    } else {
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    } else {
                                        ope = part1;
                                    }
                                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                } else {
                                    ope = parts[i];
                                }
                                cubit = Integer.valueOf(ope);
                                resets[cubit] = resets[cubit] + 1;
                            }
                        } else if(part0.contains("[") && !part0.contains(",")) {
                            parts2 = part0.split("\\[");
                            part1 = parts2[1];
                            parts2 = part1.split("\\]");
                            ope = parts2[0];
                            cubit = Integer.valueOf(ope);
                            resets[cubit] = resets[cubit] + 1;
                        } else if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                resets[i] = resets[i] + 1;
                            }
                        } else {
                            parts2 = part0.split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                                resets[cubit] = resets[cubit] + 1;
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                                for(int i=0; i<cubit; i++){
                                    resets[i] = resets[i] + 1;
                                }
                            }
                        }
                    }
                }
                for (int j=0; j<resets.length; j++) {
                    if (resets[j]!=0)
                        cubits_resets = cubits_resets + 1;
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return cubits_resets;
    }
    
    public static String buscar_cubits(String[] lines, String cubits) {
        for (String line : lines) {
            if (line.contains(cubits) && line.contains("=")) {
                String build_cubits = line.replace(" ", "");
                if(build_cubits.contains("QuantumRegister")) {
                    String[] parts = build_cubits.split("\\(");
                    String part1 = parts[1];
                    if (part1.contains(",")) {
                        parts = part1.split("\\,");
                        cubits = parts[0];                        
                    } else {
                        parts = part1.split("\\)");
                        cubits = parts[0];
                    }
                } else {
                    String[] parts = build_cubits.split("\\=");
                    cubits = parts[1];
                }
                break;
            }
        }
        return cubits;
    }
}
