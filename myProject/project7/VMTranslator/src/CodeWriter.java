import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeWriter {
    BufferedWriter br;

    static Map<String,String[]> arithAssemblyCode;
    static Map<String,Integer> macro1;
    static Map<String,String> macro2;
    static String[] pushLocalAssemblyCode = {"@addr","A=M","D=M","@SP","A=M","M=D","@SP","M=M+1"};
    static String[] popLocalAssemblyCode = {"@SP","M=M-1","@SP","A=M","D=M","@addr","A=M","M=D"};
    static String[] pushTempAssemblyCode = {"D=A","@5","D=D+A","A=D","D=M","@SP","A=M","M=D","@SP","M=M+1"};
    static String[] popTempAssemblyCode = {"D=A","@5","D=D+A","@addr","M=D","@SP","M=M-1","A=M","D=M","@addr","A=M","M=D"};
    static String[] pushStaticAssemblyCode = {"D=M","@SP","A=M","M=D","@SP","M=M+1"};
    static String[] popStaticAssemblyCode = {"@SP","M=M-1","A=M","D=M",};
    static String[] pushConstantAssemblyCode = {"D=A","@SP","A=M","M=D","@SP","M=M+1"};
    static String[] pushPointerAssemblyCode = {"D=M","@SP","A=M","M=D","@SP","M=M+1"};
    static String[] popPointerAssemblyCode = {"@SP","M=M-1","@SP","A=M","D=M"};
    static int label = 1;


    //static Map<String,String[][]> pushpopAssemblyCode;

    CodeWriter(String outputFilePath){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(outputFilePath))){
            this.br = new BufferedWriter(new FileWriter(outputFilePath));
            initAssemblyCode();
            initMacro();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void writeArithmetic(String com){
        try {
            br.write("//" + com + "\n");
            initAssemblyCode();
            String[] commands = arithAssemblyCode.get(com);
            if(com.equals("eq") || com.equals("gt") || com.equals("lt")){
                label++;
            }
            for(String command : commands){
                br.write(command + "\n");
            }
            br.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void writePushPop(String com, String arg, int index) {
        try {
            br.write("//" + com + "\n");
            String command = com.split(" ")[0];
            if (arg.equals("local") || arg.equals("argument") || arg.equals("this") || arg.equals("that")) {
                br.write("@" + macro2.get(arg) + "\n");
                br.write("D=M"+ "\n");
                br.write("@" + index + "\n");
                br.write("D=D+A"+ "\n");
                br.write("@addr"+ "\n");
                br.write("M=D"+ "\n");
                if (command.equals("push")) {
                    writeAssemblyCode(pushLocalAssemblyCode);
                } else {
                    writeAssemblyCode(popLocalAssemblyCode);
                }
            } else if (arg.equals("static")) {
                if (command.equals("push")) {
                    br.write("@" + "vm." + index + "\n");
                    writeAssemblyCode(pushStaticAssemblyCode);
                } else {
                    writeAssemblyCode(popStaticAssemblyCode);
                    br.write("@" + "vm." + index + "\n");
                    br.write("M=D" + "\n");
                }
            } else if (arg.equals("temp")) {
                br.write("@" + index + "\n");
                if (command.equals("push")) {
                    writeAssemblyCode(pushTempAssemblyCode);
                } else {
                    writeAssemblyCode(popTempAssemblyCode);
                }
            } else if (arg.equals("constant")){
                br.write("@" + index + "\n");
                writeAssemblyCode(pushConstantAssemblyCode);
            } else if (arg.equals("pointer")){
                if (command.equals("push")) {
                    br.write("@" + (index == 0 ? "THIS" : "THAT") + "\n");
                    writeAssemblyCode(pushPointerAssemblyCode);
                } else {
                    writeAssemblyCode(popPointerAssemblyCode);
                    br.write("@" + (index == 0 ? "THIS" : "THAT") + "\n");
                    br.write("M=D" + "\n");
                }
            }
            br.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeAssemblyCode(String[] assemblyCode){
        for(String command : assemblyCode){
            try {
                br.write(command + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void close() {
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void initAssemblyCode(){
        arithAssemblyCode = new HashMap<>();
        arithAssemblyCode.put("add", new String[]{"@SP", "M=M-1","A=M","D=M","@SP",
                "M=M-1","A=M","D=D+M","M=D","@SP","M=M+1"});
        arithAssemblyCode.put("sub", new String[]{"@SP", "M=M-1","@SP","A=M","D=M","@SP",
                "M=M-1","@SP","A=M","M=M-D","@SP","M=M+1"});
        arithAssemblyCode.put("neg", new String[]{"@SP", "M=M-1","@SP","A=M","D=M","D=-D",
                "M=D","@SP","M=M+1"});
        arithAssemblyCode.put("eq", new String[]{"@SP", "M=M-1","@SP","A=M","D=M","@SP",
                "M=M-1","@SP","A=M","D=M-D","@EQUAL"+label,"D;JEQ","@SP","A=M","M=0","@END"+label,"0;JMP",
                "(EQUAL"+label+")","@SP","A=M","M=-1","(END"+ label +")","@SP","M=M+1"});
        arithAssemblyCode.put("gt", new String[]{"@SP", "M=M-1","@SP","A=M","D=M","@SP",
                "M=M-1","@SP","A=M","D=M-D","@GREAT"+label,"D;JGT","@SP","A=M","M=0","@END"+label,"0;JMP",
                "(GREAT"+label+")","@SP","A=M","M=-1","(END"+ label +")","@SP","M=M+1"});
        arithAssemblyCode.put("lt", new String[]{"@SP", "M=M-1","@SP","A=M","D=M","@SP",
                "M=M-1","@SP","A=M","D=M-D","@LESS"+label,"D;JLT","@SP","A=M","M=0","@END"+label,"0;JMP",
                "(LESS"+ label +")","@SP","A=M","M=-1","(END"+ label +")","@SP","M=M+1"});
        arithAssemblyCode.put("and", new String[]{"@SP", "M=M-1","@SP","A=M","D=M","@SP",
                "M=M-1","@SP","A=M","M=D&M","@SP","M=M+1"});
        arithAssemblyCode.put("or", new String[]{"@SP", "M=M-1","@SP","A=M","D=M","@SP",
                "M=M-1","@SP","A=M","M=D|M","@SP","M=M+1"});
        arithAssemblyCode.put("not", new String[]{"@SP", "M=M-1","@SP","A=M","M=!M","@SP","M=M+1"});
    }

    static private void initMacro(){
        macro1 = new HashMap<>();
        macro1.put("local",1);
        macro1.put("argument",2);
        macro1.put("this",3);
        macro1.put("that",4);

        macro2 = new HashMap<>();
        macro2.put("local","LCL");
        macro2.put("argument","ARG");
        macro2.put("this","THIS");
        macro2.put("that","THAT");
    }
}

/* *
 * add       sub       neg      eq
 * @SP       @SP       @SP      @SP
 * M=M-1     M=M-1     M=M-1    M=M-1
 * @SP       @SP       @SP      @SP
 * A=M       A=M       A=M      A=M
 * D=M       D=M       D=M      D=M
 *           @SP       D=-D     @SP
 * M=M-1     M=M-1     M=D      M=M-1
 * @SP       @SP       @SP      @SP
 * A=M       A=M       M=M+1    A=M
 * D=D+M     M=M-D              D=D-M
 * M=D       @SP                @EQUAL
 * @SP       M=M+1              D;JEQ
 * M=M+1                        @SP
 *                              M=0
 *                              @END
 *                              0;JMP
 *                              (EQUAL)
 *                              @SP
 *                              M=-1
 *                              (END)
 *                              @SP
 *                              M=M+1
 *
 *
 * gt        lt        and      or
 * @SP       @SP       @SP      @SP
 * M=M-1     M=M-1     M=M-1    M=M-1
 * @SP       @SP       @SP      @SP
 * A=M       A=M       A=M      A=M
 * D=M       D=M       D=M      D=M
 * @SP       @SP       @SP      @SP
 * M=M-1     M=M-1     M=M-1    M=M-1
 * @SP       @SP       @SP      @SP
 * A=M       A=M       A=M      A=M
 * D=D-M     M=M-D     M=D&M    M=D|M
 * M=D       @SP       @SP      @SP
 * @SP       M=M+1     M=M+1    M=M+1
 * M=M+1
 *
 *
 * not
 * @SP
 * M=M-1
 * @SP
 * A=M
 * M=!M
 * @SP
 * M=M+1
 * */
