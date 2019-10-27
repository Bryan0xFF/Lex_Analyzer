/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jflex.*;

/**
 *
 * @author Bryan
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        File root = new File("");
        String path = root.getAbsolutePath();
        String[] rutaS = {"-parser","Sintax", path += "/src/lexCode/Sintax.cup"};
        path = root.getAbsolutePath() + "/src/lexCode/Lexer.flex";
        Generate(path, rutaS);
    }
    
    public static void Generate(String ruta, String[] sintax) throws IOException, Exception{
        /*File archivo = new File(ruta);
        jflex.Main.generate(archivo);*/
        File archivo = new File(ruta);
        jflex.Main.generate(archivo);
        java_cup.Main.main(sintax);
        
        File root = new File("");
        
        Path SymDir = Paths.get(root.getAbsolutePath() + "/src/lexCode/sym.Java");
        
        if(Files.exists(SymDir)){
            Files.delete(SymDir);
        }
        
        String path = (String) root.getAbsolutePath() + "/sym.Java";
        String path2 = root.getAbsolutePath() + "/src/lexCode/sym.Java";
        Files.move(Paths.get(path), Paths.get(path2));
        
        Path SinDir = Paths.get(root.getAbsolutePath() + "/src/lexCode/Sintax.Java");
        
        if(Files.exists(SinDir)){
            Files.delete(SinDir);
        }
        
        path = (String) root.getAbsolutePath() + "/Sintax.Java";
        path2 = root.getAbsolutePath() + "/src/lexCode/Sintax.Java";
        Files.move(Paths.get(path), Paths.get(path2));
    }
}
