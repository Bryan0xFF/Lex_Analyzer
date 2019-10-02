/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexCode;

import java.util.ArrayList;

/**
 *
 * @author Bryan
 */
public class Delete_Rec_Parser {
    
    ArrayList<String> errorCollection = new ArrayList<>();
    String lookahead = "";
    int lineaActual = 1;
    int countLinea = 0;
    int totalLineas = 0;
    ArrayList<String> data = new ArrayList<>();
    int count = 0;
    
    public Delete_Rec_Parser(ArrayList<String> data){
        this.data = data;
        this.count = Recursive_Descendent_Parser.count;
    }
    
    private void Match(String val) throws Exception{
        
        
        if(val.equals(";") && lookahead.equals("GO")){
            
            if(count + 1 < data.size()){
                 String[] temp = data.get(Recursive_Descendent_Parser.count++).split("\\|");
                 this.lookahead = temp[0];
                 lineaActual = Integer.parseInt(temp[1]);
                 return;
            }
        
        }
        
        if(val.toUpperCase().equals(lookahead)){
            if(count + 1 < data.size()){
               String[] temp = data.get(Recursive_Descendent_Parser.count++).split("\\|");
                this.lookahead = temp[0];
                lineaActual = Integer.parseInt(temp[1]);
            }
        
        }
        else{
            throw new Exception("ERROR PARSING. LINEA: " + countLinea);
        }
    }
    
    public ArrayList<String> DeleteParse() throws Exception{
        Match("");
        
        Match("FROM");
        Match("IDENTIFICADORES");
        OP();
        Match(";");
        Recursive_Descendent_Parser.lookahead = this.lookahead;
        return errorCollection;
    }

    private void OP() throws Exception{
        
        if(lookahead.equals("WHERE")){
            Match("WHERE");
            Condition();
        }
    }

    private void Condition() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals("<=")){
            Match("<=");
            E();
        }
        
        if(lookahead.equals(">=")){
            Match(">=");
            E();
        }
        
        if(lookahead.equals("<")){
            Match("<");
            E();
        }
        
        if(lookahead.equals(">")){
            Match(">");
            E();
        }
        
        if(lookahead.equals("=")){
            Match("=");
            F();
        }
        
        
    }

    private void E() throws Exception{
        if(lookahead.equals("INT")){
            Match("INT");
        }
        
        if(lookahead.equals("FLOAT")){
            Match("FLOAT");
        }
        
        if(lookahead.equals("BIT")){
            Match("BIT");
        }
    }

    private void F() throws Exception{
        if(lookahead.equals("INT")){
            Match("INT");
        }
        
        if(lookahead.equals("FLOAT")){
            Match("FLOAT");
        }
        
        if(lookahead.equals("BIT")){
            Match("BIT");
        }
        
        if(lookahead.equals("STRING")){
            Match("STRING");
        }
    }
    
}
