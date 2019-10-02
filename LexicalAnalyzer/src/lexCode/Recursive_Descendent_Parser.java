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
public class Recursive_Descendent_Parser {
    ArrayList<String> errorCollection = new ArrayList<String>();
    public static String lookahead = "";
    int lineaActual = 1;
    int countLinea = 0;
    int totalLineas = 0;
    ArrayList<String> data = new ArrayList<String>();
    public static int count = 0;
    boolean flag = false;
    
    public Recursive_Descendent_Parser(ArrayList<String> data){
        
        totalLineas = data.size() - 1;
        data.stream().map((data1) -> data1.trim().split("~")).forEach((vals) -> {
            this.data.addAll(Arrays.asList(vals));
        });
        
    }
    
    public ArrayList<String> Parse(){
        
        while(countLinea < totalLineas){
            
            try{
                if(countLinea < totalLineas){
                   countLinea = countLinea + 1;    
                }else{
                    break;
                }
                
                
                    String[] temp = data.get(count++).split("\\|");
                    lookahead = temp[0];
                    lineaActual = Integer.parseInt(temp[1]);
                    flag = true;
                
                //TODO: Aqui van los if correspondientes a: SELECT | ALTER | CREATE | DROP | TRUNCATE | ... | n
                 switch (lookahead.trim()){
            
                 case "ALTER":
                    S();
                    break;
                     
                 case "DROP":
                     Drop_Rec_Parser DRP = new Drop_Rec_Parser(data, count);
                     DRP.Parse_Drop();
                     //probar
                     break;
                     
                 case "TRUNCATE":
                     Truncate_Rec_Parser TRP = new Truncate_Rec_Parser(data, count);
                     TRP.Parse_Truncate();
                     break;
                     
                 case "CREATE":
                     Create_Rec_Parser CRP = new Create_Rec_Parser(data, count);
                     CRP.ParseCreate();
                     break;
                     
                 case "INSERT":
                     Insert_Rec_Parser IRP = new Insert_Rec_Parser(data);
                     IRP.Parse_Insert();
                     break;
                     
                 case "DELETE":
                     Delete_Rec_Parser DelRP = new Delete_Rec_Parser(data);
                     DelRP.DeleteParse();
                     break;
                     
                 case "SELECT":
                     Select_Rec_Parser SRP = new Select_Rec_Parser(data);
                     SRP.ParseSelect();
                     break;
                     
                 case "UPDATE":
                     break;
            
                }
                 
            }catch(Exception ex){
                
                errorCollection.add(ex.getMessage());
                
                for(int i = count; i < data.size(); i++){
                    
                    String val = data.get(count++).trim();
                    
                    if( val.equals(";") || val.equals("GO")){
                        break;
                    }
                    
                }
                
                if(count >= data.size()){
                    break;
                }   
            }  
        }
        
        return errorCollection;
    }
    
    private void Match(String val) throws Exception{
        
        
        if(val.equals(";") && lookahead.equals("GO")){
            
            if(count + 1 < data.size()){
                 String[] temp = data.get(count++).split("\\|");
                 lookahead = temp[0];
                 lineaActual = Integer.parseInt(temp[1]);
                 return;
            }
        
        }
        
        if(val.toUpperCase().equals(lookahead)){
            if(count + 1 < data.size()){
               String[] temp = data.get(count++).split("\\|");
                lookahead = temp[0];
                lineaActual = Integer.parseInt(temp[1]); 
            }
        
        }
        else{
            throw new Exception("ERROR PARSING. LINEA: " + countLinea);
        }
    }
    
    /*
    
    ALTER:
    
    */
    
    private void S() throws Exception{
        
        if (lookahead.equals("ALTER")){
            
            Match("ALTER");
            
            if(lookahead.equals("TABLE")){
                Match("TABLE");
                S_S();
            }
            
            if(lookahead.equals("INDEX")){
                Match("INDEX");
                K();
            }
            
            if(lookahead.equals("DATABASE")){
                Match("DATABASE");
            }
            
            
        }else{
            throw new Exception("ERROR PARSING");
        }
    }
    
    private void S_S() throws Exception{
        
        if(lookahead.equals("IDENTIFICADORES")){
            
            Match("IDENTIFICADORES");
            
            if(lookahead.equals("ALTER")){
                
                G();
                return;
                
            }
            
            Match(".");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals("ALTER")){
                
                G();
                return;
            }
            
            Match(".");
            Match("IDENTIFICADORES");
            
            G();
            
        }else{
            throw new Exception("Error en linea: " + countLinea);
        }
        
    }
    
    private void G() throws Exception{
        
        if(lookahead.equals("ALTER")){
            
            Match("ALTER");

            if(lookahead.equals("COLUMN")){
               Match("COLUMN");
                G_G(); 
            }
            
            if(lookahead.equals("INDEX")){
                Match("INDEX");
                K();
            }
        }
        
        if(lookahead.equals("ADD")){
            Match("ADD");
            N();
        }
        
        if(lookahead.equals("DROP")){
            O();
        }
        
        if(lookahead.equals("$")){
            Match("$");
        }
    }
    
    private void G_G() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                
                switch(lookahead){
                    
                    case "FLOAT": 
                        Match("FLOAT");
                        break;
                    case "INT": 
                        Match("INT");
                        break;
                    case "BIT":
                        Match("BIT");
                        break;
                    case "DATE":
                        Match("DATE");
                        break;
                        
                }
                
                Match(";");
                
            }
        }else{
            throw new Exception("ERROR PARSING, linea:" + lineaActual);
        }
    }
    
    private void K() throws Exception{
        
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                L();
                return;
            }
            
            L();
            return;
        
        }
        
        Match("ALL");
        L();
        
    }
    
    private void N() throws Exception{
        Match("IDENTIFICADORES");
        N_N();
    }
    
    private void P() throws Exception{
        if(lookahead.equals("COLUMN")){
            Match("COLUMN");
            V();
        }
        Match("INDEX");
        T();
    }
    
    private void L() throws Exception{
        if(lookahead.equals("ON")){
            Match("ON");
            L_L();
            Match(";");
        }else{
            throw new Exception("ERROR PARSING, linea:" + lineaActual);
        }
    }
    
    private void L_L() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(".")){
                Match(".");
                Match("IDENTIFICADORES");
                Match("REBUILD");
                return;
            }
            
            Match("REBUILD");
        }
    }
    
    private void N_N() throws Exception{
        
        if(lookahead.equals("FLOAT")){
            Match("FLOAT");
            
            if(lookahead.equals(",")){
                Match(",");
                Match("IDENTIFICADORES");
                N_N();
            }
            
            Match(";");
        }
        
        if(lookahead.equals("INT")){
            Match("INT");
            
            if(lookahead.equals(",")){
                Match(",");
                Match("IDENTIFICADORES");
                N_N();
            }
            
            Match(";");
        }
        
        if(lookahead.equals("BIT")){
            Match("BIT");
            
            if(lookahead.equals(",")){
                Match(",");
                Match("IDENTIFICADORES");
                N_N();
            }
            
            Match(";");
        }
        
        if(lookahead.equals("VARCHAR")){
            Match("VARCHAR");
            Match("(");
            Match("INT");
            Match(")");
            
            if(lookahead.equals(",")){
                Match(",");
                Match("IDENTIFICADORES");
                N_N();
            }
            
            Match(";");
        }
        
        if(lookahead.equals("DATE")){
            Match("DATE");
            
            if(lookahead.equals(",")){
                Match(",");
                Match("IDENTIFICADORES");
                N_N();
            }
            
            Match(";");
        }else{
            throw new Exception("ERROR PARSING, linea:" + lineaActual);
        }
        
    }
    
    private void O() throws Exception{
        
        Match("DROP");
        P();   
    }
    
    private void V() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            Q();
        }
        Match("IF");
        Match("EXISTS");
        Q();
    }
    
    private void Q() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(",")){
            Match(",");
            Q();
        }
        
        Match(";");
    }
    
    private void T() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            R();
        }
        Match("IF");
        Match("EXISTS");
        R();
    }
    
    private void R() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals("ON")){
            Match("ON");
            Match("IDENTIFICADORES");
            Match(";");
            return;
        }
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            Match(";");
            return;
        }
        Match(";");
    }
    
}
