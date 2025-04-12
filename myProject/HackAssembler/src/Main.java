import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "D:\\JavaProject\\HackAssembler\\src\\Max.asm";
        String outputFilePath = "Max.hack";
        Map<String,String> symbolTable = firstProcess(inputFilePath);
        secondProcess(symbolTable,inputFilePath,outputFilePath);
    }

    static Map<String,String> firstProcess(String inputFilePath){
        Map<String,String> labelTable = initLabelTable();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String inst;
            int address = -1;

            while ((inst = br.readLine()) != null) {
                inst = inst.strip();
                if (inst.isEmpty())continue;
                if (inst.substring(0,2).equals("//"))continue;
                if(inst.charAt(0) == '('){
                    String label = inst.substring(1,inst.length() - 1);
                    labelTable.put(label,Integer.toString(address+1));
                } else {
                    address += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return labelTable;
    }

    static void secondProcess(Map<String,String> labelTable,String inputFilePath,String outputFilePath){
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             FileWriter fw = new FileWriter(outputFilePath)) {

            Code code = new Code();
            int var = 16;
            String inst;

            while ((inst = br.readLine()) != null) {
                inst = inst.strip();
                if (inst.isEmpty())continue;
                if (inst.substring(0,2).equals("//"))continue;
                if (inst.charAt(0) == '(')continue;

                if (inst.charAt(0) == '@'){
                    String text = inst.substring(1);
                    try {
                        Integer.parseInt(text);
                        fw.write("0" + code.binary(inst.substring(1)) + "\n");
                    } catch (NumberFormatException e) {
                        if(labelTable.containsKey(text)){
                            fw.write("0" + code.binary(labelTable.get(text)) + "\n");
                        } else {
                            labelTable.put(text,Integer.toString(var));
                            fw.write("0" + code.binary(Integer.toString(var)) + "\n");
                            var++;
                        }
                    }
                } else{
                    Parser parser = new Parser(inst);
                    String comp = code.comp(parser.comp());
                    String dest = code.dest(parser.dest());
                    String jump = code.jump(parser.jump());
                    fw.write("111" + comp + dest + jump + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Map<String,String> initLabelTable() {
        Map<String,String> lableTable = new HashMap<>();
        lableTable.put("R0","0");lableTable.put("R1","1");
        lableTable.put("R2","2");lableTable.put("R3","3");
        lableTable.put("R4","4");lableTable.put("R5","5");
        lableTable.put("R6","6");lableTable.put("R7","7");
        lableTable.put("R8","8");lableTable.put("R9","9");
        lableTable.put("R10","10");lableTable.put("R11","11");
        lableTable.put("R12","12");lableTable.put("R13","13");
        lableTable.put("R14","14");lableTable.put("R15","15");

        lableTable.put("SCREEN","16384");lableTable.put("KBD","24756");
        lableTable.put("SP","0");lableTable.put("LCL","1");
        lableTable.put("ARG","2");lableTable.put("THIS","3");
        lableTable.put("THAT","4");

        return lableTable;
    }
}