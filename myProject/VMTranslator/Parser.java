import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    BufferedReader br;
    String curCommand;
    String temp;
    CommandType commandType;

    Parser(String inputFilePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            this.br = new BufferedReader(new FileReader(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //hasMoreLines();
    }

    boolean hasMoreLines() throws IOException {
        while(((temp = br.readLine()) != null)){
            if(temp.isEmpty() || temp.substring(0,2).equals("//")) continue;
            //setCommandType();
            return true;
        }
        return false;
    }

    void advance(){
        curCommand = temp;
        setCommandType();
    }

    String args1(){
        switch (commandType){
            case C_ARITHMETIC:
                return curCommand;
            case C_POP: case C_PUSH:
                return curCommand.split(" ")[1];
        }
        return "";
    }

    int args2 (){
        return Integer.parseInt(curCommand.split(" ")[2]);
    }

    CommandType commandType(){
        return commandType;
    }

    private void setCommandType(){
        String[] temp = curCommand.split(" ");
        if(temp[0].equals("push")){
            commandType = CommandType.C_PUSH;
        } else if(temp[0].equals("pop")){
            commandType = CommandType.C_POP;
        } else {
            commandType = CommandType.C_ARITHMETIC;
        }
    }
}

enum CommandType {
    C_PUSH,
    C_POP,
    C_ARITHMETIC
}

