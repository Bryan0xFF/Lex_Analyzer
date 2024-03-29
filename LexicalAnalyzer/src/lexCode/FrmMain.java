/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Bryan
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaSintax = new javax.swing.JTextArea();
        btnAnalizar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtAreaSintax.setColumns(20);
        txtAreaSintax.setRows(5);
        jScrollPane1.setViewportView(txtAreaSintax);

        btnAnalizar1.setText("Análisis Léxico y Sintáctico");
        btnAnalizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizar1ActionPerformed(evt);
            }
        });

        txtAreaOutput.setColumns(20);
        txtAreaOutput.setRows(5);
        jScrollPane2.setViewportView(txtAreaOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(btnAnalizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAnalizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(245, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizar1ActionPerformed
        // TODO add your handling code here:
        File archive = new File("Archivo.out");
        JFileChooser jf = new JFileChooser();
        String path = "";
        
        
       
        File archivo = new File(path);
         if (jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            try {
                 txtAreaOutput.setText("");
                 path = jf.getSelectedFile().getAbsolutePath();
            // TODO add your handling code here:
            Reader escanear = new BufferedReader(new FileReader(path));
            LexerLex lexer = new LexerLex(escanear);
            ArrayList<String> ListadoDeSentencias = new ArrayList();
            String sentencia = "";
            String erroresL = "";
            String erroresS = "";
            int LineaActual = 1;
            
            while (true) {
            Tokens token = lexer.yylex();
                if (token == null) {
                    String Comprobar = sentencia.replaceAll("\n", "");
                    if (!Comprobar.equals("")) {
                        sentencia += ";";
                        ListadoDeSentencias.add(sentencia);
                    }
                    break;                                     
                }
                
                int diferencia = lexer.linea - LineaActual;
                for (int i = 0; i < diferencia; i++) {
                    sentencia+= "\n";
                }
                LineaActual+= diferencia;
                //seleccionar el tipo de Token
                switch (token) {
                    //No necesito saber su valor
                     case Float: case Bit: case Int:  case String:
                        sentencia+=token + " ";
                        break;
                    //No necesito hacer nada con ellos en el análisis sintáctico
                    case COMMENT: case MULTICOMMENT:
                        break;
                        
                    case IDENTIFICADORES:
                        if (lexer.yylength() > 31) {
                            String TokenTruncado = lexer.lexeme.substring(0, 31);
                            erroresL+= "ALERTA: Indentificador Truncado|Valor: " + TokenTruncado + "|Linea: " + lexer.linea
                            + "|Columna Inicio: " + lexer.PrimeraColumna + "|Columna Fin: " + lexer.UltimaColumna + "\n";
                        }
                        sentencia+=token + " ";
                        break;
                    //Aquí si necesito el valor específico del token
                    case SIMBOLO: case RESERVADAS:
                        if (lexer.lexeme.equals("GO")||lexer.lexeme.equals(";")) {
                            String Comprobar = sentencia.replaceAll("\n", "");
                            if (!Comprobar.equals("")) {
                                sentencia+=lexer.lexeme;
                                String nuevaSentencia = sentencia;
                                ListadoDeSentencias.add(nuevaSentencia);
                                sentencia = "";
                            }                           
                        }
                        else{
                          sentencia+=lexer.lexeme + " ";
                        }
                        break;
                    
                    //Aún no sé si hacerlos parte del análisis sintáctico                    
                    case STRINGERROR:
                        sentencia+="ERROR ";                        
                        erroresL+= "STRING ERROR: Falta <'> o se encontró un salto de linea|Valor: " + lexer.lexeme + "|Linea: " + lexer.linea
                                  + "|Columna Inicio: " + lexer.PrimeraColumna + "|Columna Fin: " + lexer.UltimaColumna + "\n";
                        break;                                                                
                    case ERROR:
                        sentencia+=token + " ";
                        erroresL+= "ERROR: cadena no reconocida|Valor: "+lexer.lexeme+"|Linea: "+lexer.linea
                                +"|Columna Inicio: "+lexer.PrimeraColumna+"|Columna Fin: "+lexer.UltimaColumna+"\n";
                        break;
                    default:
                        throw new AssertionError();
                        
                    //Sólo avisar del error léxico pero no lo necesito en el sintáctico
                    case ERRORCOMMENT:
                        sentencia+="ERROR "; 
                        erroresL+= "ERROR: Comentario Multilinea sin cerrar|Linea Inicial: " + lexer.linea+"\n";
                        break;
                }
            }
            //COMIENZA EL ANÁLISIS SINTÁCTICO                   
            int i = 0;
            int UltimaLinea = 1;
            while(i<ListadoDeSentencias.size()){
                String SentenciaActual = ListadoDeSentencias.get(i);                                
                boolean HayError = false;
                if (SentenciaActual.contains("ERROR")) {
                    HayError = true;
                }
                if (!HayError) {
                    //Mandar a JCUP
                    Sintax s = new Sintax(new lexCode.Lexer(new StringReader(SentenciaActual))); 
                    try {
                        s.parse();
                    } catch (Exception ex) {
                        Symbol sym = s.getSymbol();
                        erroresS += "\nERROR: Unexpected Token:  <"+sym.value+">. At Line:  "+(UltimaLinea+sym.right)+".  At Column:  "+(sym.left);
                    }
                }
                
      //Me sirve únicamente para mostar correctamente la línea de error
      //pues mandaré statement por statement a JCUP y este devolverá una línea
      //la cual será sumada a la "UltimaLinea" que hace referencia a la linea
      //donde terminó el último statement, sin importar si se parseó correctamente
      //o si no se mandó a analizar
                String Separar = SentenciaActual.replaceAll("\n", " \n ");            
                String Revisar [] = Separar.split(" ");                               
                int Contador = 0;                                                     
                for (String Revisar1 : Revisar) {                                     
                    if (Revisar1.equals("\n")) {                                      
                        Contador++;                                                   
                    }                                                                 
                }                                                                     
                UltimaLinea += Contador;                                              
                
                i++;
            }            
            if (erroresS.equals("") && erroresL.equals("")) {
                JOptionPane.showMessageDialog(null,"Se parseó correctamente");
            }
            else{
                JOptionPane.showMessageDialog(null,"Se parseó con errores");
                txtAreaOutput.setText("LEXICAL ERRORS: \n"+erroresL +"\n\n SINTAX ERROR(S):"+erroresS);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    else{
        txtAreaOutput.setText("ALERTA: No se ha seleccionado un Archivo aún");
    }
        
    }//GEN-LAST:event_btnAnalizar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }
    
    private void LexicalAnalyze(){
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtAreaOutput;
    private javax.swing.JTextArea txtAreaSintax;
    // End of variables declaration//GEN-END:variables
}
