public class Parser {
    private String comp;
    private String dest;
    private String jump;

    Parser(){}

    Parser(String text){
        process(text);
    }

    String comp(){
        return comp;
    }

    String dest(){
        return dest;
    }

    String jump(){
        return jump;
    }

    void process(String inst){
        inst = inst.strip();

        if(inst.contains(";")){
            jump = inst.split(";")[1];
            inst = inst.split(";")[0];
        } else {
            jump = "";
        }
        if(inst.contains("=")){
            comp = inst.split("=")[1];
            dest = inst.split("=")[0];
        } else {
            dest = "";
            comp = inst;
        }
    }
}
