package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import java.io.IOException;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCM;

public class NºCMsensor implements Sensor {

   @Override
   public void describe(SensorDescriptor descriptor) {
      descriptor.name("Calcular el número de cúbits con alguna operación Measure");
   }

   @Override
   public void execute(SensorContext context) {
      FileSystem fs = context.fileSystem();
      Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
      for (InputFile file : files) {
         try {
            context.<Integer>newMeasure()
            .forMetric(NºCM)
            .on(file)
            .withValue(calculate(file, context))
            .save();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public static int calculate(InputFile f, SensorContext context) throws IOException {
      int[] measures = new int[100];
      String part0, part1, cubits;
      String[] parts, parts2;
      int cubit, cubits_measure = 0;
      if (f.isFile()) {
         try {
            String content = f.contents();
            String[] lines = content.split("\\R");
            for (String line : lines) {
               if (line.contains(".measure(")) {
                  part0 = line.replace(" ", "");
                  if(part0.contains("range(")){
                     parts2 = part0.split("\\(");
                     part1 = parts2[2];
                     parts2 = part1.split("\\)");
                     cubits = parts2[0];
                     if(cubits.matches("[+-]?\\d*(\\.\\d+)?")){
                        cubit = Integer.valueOf(cubits);
                     } else {
                        cubits = buscar_cubits(lines, cubits);
                        cubit = Integer.valueOf(cubits);
                     }
                     measures[cubit] = measures[cubit] + 1;
                  } else{
                     parts = part0.split("\\(");
                     part0 = parts[1];
                     parts = part0.split("\\,");
                     if(parts.length == 2) {
                        if(parts[0].contains("[")) {
                           parts2 = parts[0].split("\\[");
                           part1 = parts2[1];
                           parts2 = part1.split("\\]");
                           cubits = parts2[0];
                           cubit = Integer.valueOf(cubits);
                           measures[cubit] = measures[cubit] + 1;
                        }
                        else if(parts[0].matches("[+-]?\\d*(\\.\\d+)?")){
                           cubit = Integer.valueOf(parts[0]);
                           measures[cubit] = measures[cubit] + 1;
                        } else {
                           cubits = buscar_cubits(lines, parts[0]);
                           cubit = Integer.valueOf(cubits);
                           for(int j=0; j<cubit; j++) {
                              measures[j] = measures[j] + 1; 
                           }
                        }
                     } else {
                         for(int i=0; i<(parts.length/2); i++){
                            if(parts[i].contains("[")) {
                               parts2 = parts[i].split("\\[");
                               if(parts2.length == 2) {
                                  part0 = parts2[1];
                               } else {
                                  part0 = parts2[2];
                               }
                               if(part0.contains("]")){
                                  parts2 = part0.split("\\]");
                                  cubits = parts2[0];
                               } else {
                                  cubits = part0;
                               }
                               cubit = Integer.valueOf(cubits);
                            }
			    else if(parts[i].contains("]") && !parts[i].contains("[")) {
                               parts2 = parts[i].split("\\]");
                               cubits = parts2[0];
                            } else {
                               cubits = parts[i];
                            }
                            cubit = Integer.valueOf(cubits);
                            measures[cubit] = measures[cubit] + 1;
                         }
                      }
                   }
                }
             }
             for(int j=0; j<measures.length; j++) {
                if(measures[j]!=0)
                   cubits_measure = cubits_measure + 1;
             }
          } catch (IOException e) {
             throw new IllegalStateException(e);
          }
       }
       return cubits_measure;
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
