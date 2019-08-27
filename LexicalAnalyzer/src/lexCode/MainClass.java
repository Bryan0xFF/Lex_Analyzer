/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexCode;

import java.io.File;
import jflex.*;

/**
 *
 * @author Bryan
 */
public class MainClass {
    public static void main(String[] args) {
        File root = new File("");
        String path = root.getAbsolutePath();
        path += "/src/lexCode/Lexer.flex";
        GenLex(path);
    }
    
    public static void GenLex(String ruta){
        File archivo = new File(ruta);
        jflex.Main.generate(archivo);
    }
}
