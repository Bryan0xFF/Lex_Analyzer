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
/**
 *
 * @author Bryan
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        File root = new File("");
        String path = "C:\\Users\\Bryan\\OneDrive - Universidad Rafael Landivar\\Documentos\\NetBeansProjects\\Lex_Analyzer\\LexicalAnalyzer\\src\\lexCode\\Sintax.cup";
        String[] rutaS = {"-parser","Sintax", path};
        path = root.getAbsolutePath() + "/src/lexCode/Lexer.flex";
        
        String pathLex = root.getAbsolutePath() + "/src/lexCode/LexerLex.flex";
        Generate(path, rutaS, pathLex);
    }
    
    public static void Generate(String ruta, String[] sintax, String pathLex) throws IOException, Exception{
        /*File archivo = new File(ruta);
        jflex.Main.generate(archivo);*/
        File archivo = new File(ruta);
        jflex.Main.generate(archivo);
        java_cup.Main.main(sintax);
        
        File archivo2 = new File(pathLex);
        jflex.Main.generate(archivo2);
        
        File root = new File("");
        
        Path SymDir = Paths.get(root.getAbsolutePath() + "/src/lexCode/sym.java");
        
        if(Files.exists(SymDir)){
            Files.delete(SymDir);
        }
        
        String path = root.getAbsolutePath() + "/sym.java";
        String path2 = root.getAbsolutePath() + "/src/lexCode/sym.java";
        Files.move(Paths.get(path), Paths.get(path2));
        
        Path SinDir = Paths.get(root.getAbsolutePath() + "/src/lexCode/Sintax.java");
        
        if(Files.exists(SinDir)){
            Files.delete(SinDir);
        }
        
        path = root.getAbsolutePath() + "/Sintax.java";
        path2 = root.getAbsolutePath() + "/src/lexCode/Sintax.java";
        Files.move(Paths.get(path), Paths.get(path2));
    }
}
