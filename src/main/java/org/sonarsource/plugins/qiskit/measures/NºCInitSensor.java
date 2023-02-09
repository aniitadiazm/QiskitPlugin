package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import java.io.IOException;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCInit;

public class NºCInitSensor implements Sensor {

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Calcular el número de cúbits con alguna operación Initialize");
    }

    @Override
    public void execute(SensorContext context) {
        FileSystem fs = context.fileSystem();
        // only "main" files, but not "tests"
        Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
        for (InputFile file : files) {
            try {
                context.<Integer>newMeasure()
                    .forMetric(NºCInit)
                    .on(file)
                    .withValue(calculate(file, context))
                    .save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int calculate(InputFile f, SensorContext context) throws IOException {
        int[] inits = new int[100];
        String build_cubits, part1, cubits;
        String[] parts, parts2, parts3;
        int cubit, cubits_inits = 0;
        if (f.isFile()) {
            try {
                String content = f.contents();
                String[] lines = content.split("\\R");
                for (String line : lines) {
                    if (line.contains(".initialize(")) {
                        build_cubits = line.replace(" ", "");
                        if(build_cubits.contains("range(")){
                            parts2 = build_cubits.split("\\(");
                            part1 = parts2[2];
                            parts2 = part1.split("\\)");
                            cubits = parts2[0];
                            if(cubits.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(cubits);
                            } else {
                                cubits = buscar_cubits(lines, cubits);
                                cubit = Integer.valueOf(cubits);
                            }
                            for(int i=0; i<cubit; i++) {
                                inits[i] = inits[i] + 1;
                            }
                        } else {
                            parts = build_cubits.split("\\(");
                            part1 = parts[1];
                            parts = part1.split("\\)");
                            part1 = parts[0];
                            parts2 = part1.split("\\,");
                            if(parts2.length == 2) {
                                if(parts2[1].contains("[")) {
                                    parts2 = parts2[1].split("\\[");
                                    part1 = parts2[1];
                                    parts2 = part1.split("\\]");
                                    cubits = parts2[0];
                                    cubit = Integer.valueOf(cubits);
                                    inits[cubit] = inits[cubit] + 1;
                                }
                                else if(parts2[1].matches("[+-]?\\d*(\\.\\d+)?")){
                                    cubit = Integer.valueOf(parts2[1]);
                                    inits[cubit] = inits[cubit] + 1;
                                }
                                else {
                                    cubits = buscar_cubits(lines, parts2[1]);
                                    cubit = Integer.valueOf(cubits);
                                    for(int j=0; j<cubit; j++) {
                                        inits[j] = inits[j] + 1; 
                                    }
                                }
                            } else if(parts2.length > 2){
                                if(parts2[0].contains("[")){
                                    int n = 0;
                                    for(int i=0; i<parts2.length; i++) {
                                        if(parts2[i].contains("]")){
                                            n = i+1;
                                            break;
                                        }
                                    }
                                    for(int j=n; j<parts2.length; j++) {
                                        if(parts2[j].contains("[")){
                                            parts3 = parts2[j].split("\\[");
                                            if(parts3.length == 2){
                                                part1 = parts3[1];
                                            } else {
                                                part1 = parts3[2];
                                            }
                                            if(part1.contains("]")){
                                                parts3 = part1.split("\\]");
                                                cubits = parts3[0];
                                            } else {
                                                cubits = part1;
                                            }
                                        } else if(parts2[j].contains("]") && !parts2[j].contains("[")) {
                                            parts3 = parts2[j].split("\\]");
                                            cubits = parts3[0];
                                        } else {
                                            cubits = parts2[j];
                                        }
                                        if(cubits.matches("[+-]?\\d*(\\.\\d+)?")){
                                            cubit = Integer.valueOf(cubits);
                                            inits[cubit] = inits[cubit] + 1;
                                            break;
                                        } else {
                                            cubits = buscar_cubits(lines, cubits);
                                            cubit = Integer.valueOf(cubits);
                                            for(int i=0; i<cubit; i++){
                                                inits[i] = inits[i] + 1;
                                            }
                                            break;
                                        }                              
                                    }
                                } else {
                                    for(int i=1; i<parts2.length; i++) {
                                        if(parts2[i].contains("[")){
                                            parts3 = parts2[i].split("\\[");
                                            if(parts3.length == 2){
                                                part1 = parts3[1];
                                            } else {
                                                part1 = parts3[2];
                                            }
                                            if(part1.contains("]")){
                                                parts3 = part1.split("\\]");
                                                cubits = parts3[0];
                                            } else {
                                                cubits = part1;
                                            }
                                        } else if(parts2[i].contains("]") && !parts2[i].contains("[")) {
                                            parts3 = parts2[i].split("\\]");
                                            cubits = parts3[0];
                                        } else {
                                            cubits = parts2[i];
                                        }
                                        cubit = Integer.valueOf(cubits);
                                        inits[cubit] = inits[cubit] + 1;
                                    }
                                }
                            }
                        }
                    }
                }
                for (int j=0; j<inits.length; j++) {
                    if (inits[j]!=0)
                        cubits_inits = cubits_inits + 1;
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return cubits_inits;
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
