# Ambiente de pruebas

### Instalar
0. [IntelliJ Community](https://www.jetbrains.com/es-es/idea/download/#section=windows)
1. [Git Windows](https://git-scm.com/download/win)
2. [Node JS](https://nodejs.org/es/download/)
    * Ejecutar en cmd: node -v
3. Appium
    * Ejecutar en consola: npm install -g appium
4. [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
    * Definir variables de entorno
        * JAVA HOME: C:\Program Files\Java\jdk-version
        * %JAVA_HOME%
        * Ejecutar el comando javac en cmd para verificar configuración
5. [Android studio](https://developer.android.com/studio/index.html)
    * Abrir por primera vez para que instale el ANDROID JDK
    * Definir variables de entorno
        * ANDOIRD_HOME: C:\Users\Usuario\AppData\Local\Android\Sdk        
        * %ANDROID_HOME%\platform-tools
        * %ANDROID_HOME%
6. Appium Doctor
    * Ejecutar en cmd: npm install -g appium-doctor
    * cmd: appium-doctor
   
### Clonar Repo
0. Generar una Shh y registrarla en su cuenta de donde esta clonando este repositorio (Bitbucket, GitHub, gitLab)
   * [Windows](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/#platform-windows)
   * [GNU/Linux](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/#platform-linux)
   * [MacOS](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/#platform-mac)

1. git bash: git clone direccionShhRepo

   1.1 Para MAC para evitar el mensaje "no reconoce chromedriver":
Abrimos la terminal y nos vamos hacia la carpeta donde se encuentra el chrome driver y corremos el siguiente comando
    * xattr -d com.apple.quarantine chromedriver

### Ejecutar Appium
1. cmd: appium
2. Seleccion de dispositivo:
   * En caso de ser físico
      * El disposito debe estar registrado en el archivo:
   * En caso de ser Kobiton
      * Configurar el data provider ya sea en la clase o el xml
3. Asegurarse que el package y activity estan configurados en el archivo:
4. Ejecute su prueba con confianza

### Ejecutar web
1. Seleccion de navegador
   * Configurar el data provider ya sea en la clase o el xml
2. Ejecute su prueba con confianza

### Pruebas API
1. Importe la libreria de manipulación de APIS
2. Ejecute su prueba con confianza

### Créditos
Este repositorio es el resultado del conocimiento colectivo de muchas personas
y tu puedes ser una de ellas, si tienes algun aporte no dudes en escribir al dueño
del repositorio.
Personas que juntaron este conocimiento:
Carlos cotto, Angel Torre, Jhonatan Flores, Carlos Arroyave, Christian Brolo.