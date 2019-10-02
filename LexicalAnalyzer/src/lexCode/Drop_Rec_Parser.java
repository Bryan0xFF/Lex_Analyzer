/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexCode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Bryan
 */
public class Drop_Rec_Parser {
    
    ArrayList<String> errorCollection = new ArrayList<>();
    String lookahead = "";
    int lineaActual = 1;
    int countLinea = 0;
    int totalLineas = 0;
    ArrayList<String> data = new ArrayList<>();
    int count = 0;
    
    public Drop_Rec_Parser(ArrayList<String> data, int count){
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
    
    public ArrayList<String> Parse_Drop() throws Exception{
        Match("");
        
        if(lookahead.equals("DATABASE")){
            S();
        }
        
        if(lookahead.equals("INDEX")){
            S_S();
        }
        
        if(lookahead.equals("LOGIN")){
            Match("DROP");
            Match("LOGIN");
            Match("IDENTIFICADORES");
            Match(";");
        }
        
        if(lookahead.equals("TABLE")){
            Table();
        }
        Recursive_Descendent_Parser.lookahead = this.lookahead;
        return errorCollection;
    }
    
    private void S() throws Exception{
        Match("DATABASE");
        Match("IDENTIFICADORES");
        
        if(lookahead.equals("IDENTIFICADORES")){
           Match("IDENTIFICADORES");
            G(); 
        }
        Match(";"); 
    }
    
    private void G() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(",")){
            H();
        }
        
        if(lookahead.equals(";")){
            Match(";");
            return;
        }
        
        if(lookahead.equals("GO")){
            Match("GO");
        }
    }
    
    private void H() throws Exception{
        
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(",")){
            Match(",");
            H();
        }
        
        Match(";");
        
    }
    
    private void S_S() throws Exception{
        
        if(lookahead.equals("IF")){
            Match("IF");
            Match("Exists");
            K();
            return;
        }
        
        K();
        
        
    }

    private void K() throws Exception{
        
        if(lookahead.equals("IDENTIFICADORES")){
            
           Match("IDENTIFICADORES");
           Match("ON");
           H_H();
           return;
        }
        
        J();
        
    }

    private void H_H() throws Exception{
        
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(",")){
                L();
                return;
            }
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(",")){
                L();
                return;
            }
            
            if(lookahead.equals(";")){
                Match(";");
                return;
            }
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                
                if(lookahead.equals(";")){
                    Match(";");
                    return;
                }
                
                if(lookahead.equals(",")){
                    Match(",");
                    L();
                    return;
                }
            }
        }
        
        if(lookahead.equals(";")){
            Match(";");
        }
        
        
    }

    private void J() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(",")){
            Match(",");
            K();
        }
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(",")){
                Match(",");
                K();
            }
            
            if(lookahead.equals(";")){
                Match(";");
                return;
            }
        }
        
        if(lookahead.equals(";")){
            Match(";");
        }
    }

    private void L() throws Exception{
        
    
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(",")){
                K();
                return;
            }
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(",")){
                K();
                return;
            }
            
            
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                
                
                
                if(lookahead.equals(",")){
                    Match(",");
                    K();
                    return;
                }
            }
        }
        
        if(lookahead.equals(";")){
            Match(";");
        }
    }

    private void Table() throws Exception{
        Match("TABLE");
        SYN();
    }

    private void SYN() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            Names();
        }
        
        Match("IF");
        Match("EXISTS");
        Names();
    }

    private void Names() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                
                if(lookahead.equals(",")){
                    Rep_Names();
                    return;
                }
                
                Match(";");
                return;
            }
            
            if(lookahead.equals(",")){
                Match(",");
                Rep_Names();
                return;
            }
        }
        
        if(lookahead.equals(",")){
            Match(",");
            Rep_Names();
            return;
        }
        
        Match(";");
    }

    private void Rep_Names() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                
                if(lookahead.equals(",")){
                    Rep_Names();
                    return;
                }
                
                Match(";");
                return;
            }
            
            if(lookahead.equals(",")){
                Match(",");
                Rep_Names();
                return;
            }
        }
        
        if(lookahead.equals(",")){
            Match(",");
            Rep_Names();
            return;
        }
        
        Match(";");
    }
}

    
    

