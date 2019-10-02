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
public class Create_Rec_Parser {
    
    ArrayList<String> errorCollection = new ArrayList<>();
    String lookahead = "";
    int lineaActual = 1;
    int countLinea = 0;
    int totalLineas = 0;
    ArrayList<String> data = new ArrayList<>();
    int count = 0;
    
    public Create_Rec_Parser(ArrayList<String> data, int count){
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
        
        if(val.equals(lookahead)){
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
    
    public ArrayList<String> ParseCreate() throws Exception{
            Match("");
        
        if(lookahead.equals("VIEW") || lookahead.equals("OR")){
            
            S();
        }
        
        if(lookahead.equals("USER")){
            Match("USER");
            Match("IDENTIFICADORES");
            Match(";");
        }
        
        if(lookahead.equals("DATABASE")){
            DB();
        }
        
        if(lookahead.equals("TABLE")){
            Match("TABLE");
        }
        Recursive_Descendent_Parser.lookahead = this.lookahead;
        return errorCollection;
    }

    private void S() throws Exception{
        
        if(lookahead.equals("OR")){
            Match("OR");
            Match("ALTER");
            Match("VIEW");
            ID();
            return;
        }
        
        Match("VIEW");
        ID();
        
    }

    private void ID() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(".")){
            Match(".");
            Match("IDENTIFICADORES");
            K();
            return;
        }
        
        K();
    }

    private void K() throws Exception{
        
        if(lookahead.equals("(")){
            Match("(");
            Match("IDENTIFICADORES");
            
            if(lookahead.equals(",")){
                Match(",");
                H();
                Match(")");
                J();
                return;
            }
            
            Match(")");
            J();
            return;
        }
        
        J();
        
    }

    private void H() throws Exception{
        Match("IDENTIFICADORES");
        
        if(lookahead.equals(",")){
            Match(",");
            H();
            return;
        }
        Match("IDENTIFICADORES");
    }

    private void J() throws Exception{
        Match("AS");
        
        SELECT();
    }

    private void SELECT() throws Exception{
        
    }

    private void DB() throws Exception{
        Match("DATABASE");
        Match("IDENTIFICADORES");
        OP();
        
    }

    private void OP() throws Exception{
        
        if(lookahead.equals(";")){
            Match(";");
            return;
        }
        
        if(lookahead.equals("CONTAINMENT")){
            Match("CONTAINMENT");
            Match("=");
            CT();
            Match(";");
            return;
        }
        
        File();
        
    }

    private void CT() throws Exception{
        if(lookahead.equals("NONE")){
            Match("NONE");
            return;
        }
        
        if(lookahead.equals("PARTIAL")){
            Match("PARTIAL");
            return;
        }
        
        File();
    }

    private void File() throws Exception{
        Match("ON");
        Op_File();
    }

    private void Op_File() throws Exception{
        if(lookahead.equals("PRIMARY")){
            Match("PRIMARY");
            Match("(");
            Filespec();
            Match(")");
            
            if(lookahead.equals(",")){
                Op_File();
                return;
            }
            return;
        }
        
        Filespec();
    }

    private void Filespec() throws Exception{
        Match("NAME");
        Match("=");
        Match("IDENTIFICADORES");
        Match(",");
        Match("FILENAME");
        Match("=");
        Match("IDENTIFICADORES");
        Op_FileSpec();
        Op_Max();
        FileGR();
    }

    private void Op_Max() throws Exception{
       if(lookahead.equals(",")){
           Match(",");
           Match("MAXSIZE");
           Match("=");
           Match("INT");
           Op_Size();
       }
    }

    private void FileGR() throws Exception{
        if(lookahead.equals(",")){
            Match(",");
            Match("FILEGROWTH");
            Match("=");
            Match("INT");
            Op_Size();
        }
    }

    private void Op_FileSpec() throws Exception{
        if(lookahead.equals(",")){
            Match(",");
            Match("SIZE");
            Match("=");
            Match("INT");
            Op_Size();
        }
    }

    private void Op_Size() throws Exception{
        if(lookahead.equals("IDENTIFICADORES")){
            Match("IDENTIFICADORES");
        }   
    }
    
    
    
}
