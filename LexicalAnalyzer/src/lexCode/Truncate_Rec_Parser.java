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
public class Truncate_Rec_Parser {
    
    ArrayList<String> errorCollection = new ArrayList<>();
    String lookahead = "";
    int lineaActual = 1;
    int countLinea = 0;
    int totalLineas = 0;
    ArrayList<String> data = new ArrayList<>();
    int count = 0;
    
    Truncate_Rec_Parser(ArrayList<String> data, int count){
        this.data = data;
        this.count = Recursive_Descendent_Parser.count;
    }
    
    private void Match(String val) throws Exception{
        
        
        if(val.equals(";") && lookahead.equals("GO")){
            
            if(count + 1 < data.size()){
                 String[] temp = data.get(Recursive_Descendent_Parser.count++).split("\\|");
                 lookahead = temp[0];
                 lineaActual = Integer.parseInt(temp[1]);  
                 return;
            }
        
        }
        
        if(val.toUpperCase().equals(lookahead)){
            if(count + 1 < data.size()){
               String[] temp = data.get(Recursive_Descendent_Parser.count++).split("\\|");
                lookahead = temp[0];
                lineaActual = Integer.parseInt(temp[1]); 
            }
        
        }
        else{
            throw new Exception("ERROR PARSING. LINEA: " + countLinea);
        }
    }
    
    public ArrayList<String> Parse_Truncate() throws Exception{
        Match("");
        S();
        Recursive_Descendent_Parser.lookahead = this.lookahead;
        return errorCollection;
    }

    private void S() throws Exception{
        
        Match("TRUNCATE");
        Match("TABLE");
        
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                Match(";");
                return;
            }
            
            Match(";");
            return;
        }
        
        Match(";");
    }
    
}
