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
public class Select_Rec_Parser {
    
    ArrayList<String> errorCollection = new ArrayList<>();
    String lookahead = "";
    int lineaActual = 1;
    int countLinea = 0;
    int totalLineas = 0;
    ArrayList<String> data = new ArrayList<>();
    int count = 0;
    
    public Select_Rec_Parser(ArrayList<String> data){
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
    
    public ArrayList<String> ParseSelect() throws Exception{
        Match("");
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            
            if(lookahead.equals("AS")){
                AS();
                
                if(lookahead.equals(",")){
                    Match(",");
                    ParseSelect();
                }
            }
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                
                if(lookahead.equals("AS")){
                    AS();
                }
                
                if(lookahead.equals(".")){
                    Match(".");
                    Match("IDENTIFICADORES");
                    
                    if(lookahead.equals("AS")){
                        AS();
                    }
                }
            }
        }
        
        if(lookahead.equals("COUNT")){
            Match("COUNT");
            FuncCount();
        }
        
        if(lookahead.equals("MAX")){
            Match("MAX");
            Func();
        }
        
        if(lookahead.equals("AVG")){
            Match("AVG");
            Func();
        }
        
        if(lookahead.equals("MIN")){
            Match("MIN");
            Func();
        }
        
        if(lookahead.equals("IDENTIFICADORES")){
            Repeat_Sentence();
        }
        
        Match("FROM");
        TABLE();
        Match(";");
        Recursive_Descendent_Parser.lookahead = this.lookahead;
        return errorCollection;
    }

    private void AS() throws Exception{
        Match("AS");
        
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            return;
        }
        
        if(lookahead.equals("*")){
            Match("*");
            return;
        }
        
        Match("[");
        Match("IDENTIFICADORES");
        S();
        Match("]");
        
    }

    private void S() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            S();
        }
    }

    private void TABLE() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(".")){
             Match(".");
             Match("IDENTIFICADORES");   
            }
            
        }
        
    }

    private void Func() throws Exception{
        Match("(");
        Match("IDENTIFICADORES");
        Match(")");
        
    }

    private void FuncCount() throws Exception{
        Match("(");
        Match("*");
        Match(")");
    }
    
    private void Repeat_Sentence() throws Exception{
        
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            
            if(lookahead.equals("AS")){
                AS();
                
                if(lookahead.equals("IDENTIFICADORES")){
                    Repeat_Sentence();
                }
            }
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                
                if(lookahead.equals("AS")){
                    AS();
                }
                
                if(lookahead.equals(".")){
                    Match(".");
                    Match("IDENTIFICADORES");
                    
                    if(lookahead.equals("AS")){
                        AS();
                    }
                }
            }
        }
        
        if(lookahead.equals("*")){
            Match("*");
            if(lookahead.equals("COUNT")){
            Match("COUNT");
            FuncCount();
        }
        
        if(lookahead.equals("MAX")){
            Match("MAX");
            Func();
        }
        
        if(lookahead.equals("AVG")){
            Match("AVG");
            Func();
        }
        
        if(lookahead.equals("MIN")){
            Match("MIN");
            Func();
        }
        return;
        }
        
        if(lookahead.equals("COUNT")){
            Match("COUNT");
            FuncCount();
        }
        
        if(lookahead.equals("MAX")){
            Match("MAX");
            Func();
        }
        
        if(lookahead.equals("AVG")){
            Match("AVG");
            Func();
        }
        
        if(lookahead.equals("MIN")){
            Match("MIN");
            Func();
        }
        
        if(lookahead.equals("IDENTIFICADORES")){
            Repeat_Sentence();
        }
    }
    
}
