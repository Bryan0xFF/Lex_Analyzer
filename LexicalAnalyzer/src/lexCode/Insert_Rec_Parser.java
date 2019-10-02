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
public class Insert_Rec_Parser {
    
     ArrayList<String> errorCollection = new ArrayList<>();
    String lookahead = "";
    int lineaActual = 1;
    int countLinea = 0;
    int totalLineas = 0;
    ArrayList<String> data = new ArrayList<>();
    int count = 0;
    
    public Insert_Rec_Parser(ArrayList<String> data){
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
    
    public ArrayList<String> Parse_Insert() throws Exception{
        Match("");
        
        Match("INTO");
        Match("IDENTIFICADORES");
        InsParams();
        Recursive_Descendent_Parser.lookahead = this.lookahead;
        return errorCollection;
    }

    private void InsParams() throws Exception{
        Match("(");
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            RecParams();
        }
        
        if(lookahead.equals("INT")){
            Match("INT");
            RecParams();
        }
        Match(")");
        InsValues();
    }

    private void RecParams() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            
            if(lookahead.equals("IDENTIFICADORES")){
                Match("IDENTIFICADORES");
                
                if(lookahead.equals("IDENTIFICADORES")){
                    RecParams();
                    return;
                }
            }
             
            if(lookahead.equals("INT")){
                RecParams();
                return;
            }
                
            if(lookahead.equals("IDENTIFICADORES")){
                RecParams();
                return;
            }
                
            if(lookahead.equals("STRING")){
                RecParams();
            }
            
            if(lookahead.equals(",")){
                RecParams();
            }
        }
        
        if(lookahead.equals("STRING")){
                 Match("STRING");
                 
                 if(lookahead.equals("STRING")){
                     RecParams();
                     return;
                 }
                 
                 if(lookahead.equals("INT")){
                    RecParams();
                    return;
                }
                
                if(lookahead.equals("IDENTIFICADORES")){
                    RecParams();
                    return;
                }
        }
        
        if(lookahead.equals("INT")){
            Match("INT");
                
            if(lookahead.equals("INT")){
                RecParams();
                return;
            }
                
            if(lookahead.equals("IDENTIFICADORES")){
                RecParams();
            }
            
            if(lookahead.equals("STRING")){
                RecParams();
            }
        }
    }

    private void InsValues() throws Exception{
        Match("VALUES");
        Match("(");
        RecParams();
        Match(")");
        Match(";");
    }
}
