# Analizador Léxico

- La ruta está configurada dinamicamente para buscar el Lexer.flex dentro del proyecto.
- Se ejecuta el programa en:
````Java
        File root = new File("");
        String path = root.getAbsolutePath();
        path += "/src/lexCode/Lexer.flex";
        GenLex(path);
````

- Al iniciar el programa a correr, esto *solamente* correrá la creación del Lexer.Java, por lo cual se deberá correr
manualmente el archivo FrmMain.java para que inicie la compilación gráfica del GUI.

- Dentro del programa, se deberá presionar el botón para localizar el archivo que se desea analizar, luego dentro del mismo
proyecto, se creara un archivo .OUT y en la ventana se mostrará inmediatamente el resultado del análisis lexicográfico.


