import java.io.IOException;

public class VMTranslator {
    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[0].split("\\.")[0] + ".asm";

        try {
            Parser parser = new Parser(inputFilePath);
            CodeWriter codeWriter = new CodeWriter(outputFilePath);
            while(parser.hasMoreLines()){
                parser.advance();
                if(parser.commandType() == CommandType.C_ARITHMETIC){
                    codeWriter.writeArithmetic(parser.curCommand);
                } else if(parser.commandType() == CommandType.C_POP || parser.commandType() == CommandType.C_PUSH){
                    codeWriter.writePushPop(parser.curCommand, parser.args1(), parser.args2());
                }
            }
            codeWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}