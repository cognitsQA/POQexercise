# Testing Envirnonment

### Install
0. [IntelliJ Community](https://www.jetbrains.com/es-es/idea/download/#section=windows)
1. [Git Windows or macOS](https://git-scm.com/downloads)
2. [Apple developer account](https://developer.apple.com).(macOS only)
3. [Xcode](https://developer.apple.com/xcode/).(macOS only)
4. [Node JS](https://nodejs.org/es/download/)
    * Execute in cmd: node -v
5. Appium
    * Execute in cmd: npm install -g appium
6. [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
    * Define Environment Variables Windows:
        * JAVA HOME: C:\Program Files\Java\jdk-version
        * %JAVA_HOME%
        * Execute javac command in cmd to verify configuration.
    * Define Environment Variables macOS: https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/
      
7. [Android studio](https://developer.android.com/studio/index.html)
    * Open for the first time and install ANDROID JDK
    * Define Environment Variables Windows: 
        * ANDOIRD_HOME: C:\Users\User\AppData\Local\Android\Sdk        
        * %ANDROID_HOME%\platform-tools
        * %ANDROID_HOME%
    * Define Environment Variables macOS: https://stackoverflow.com/questions/28296237/set-android-home-environment-variable-in-mac
8. Appium Doctor
    * Execute in cmd: npm install -g appium-doctor
    * cmd: appium-doctor
   
### Clone Repo
0. Generate a Shh and register in the account where you are clonning this repo. (i.e. Bitbucket, GitHub, gitLab)
   * [Windows](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/#platform-windows)
   * [GNU/Linux](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/#platform-linux)
   * [MacOS](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/#platform-mac)

1. git bash: git clone direccionShhRepo

   1.1 For macOS to evade the "chromedriver is not recognized" message:
open the terminal and navigate to the folder where the chromedriver is and excute the following commnad:
    * xattr -d com.apple.quarantine chromedriver

### Execute Appium
1. cmd: appium
2. Device selection:
   * In case of being a physical device:
      * The device must be registered in the following file: localconfiguration 
   * In case of being a Kobiton:
      * Configure the data provider in the java class or the xml
3.Make sure the package and activity are configrued.
4. Excute your test.

### Execute web
1. Browser selection
   * Configure the data provider in the java class or the xml.
2. Execute your test.

### API Tests
1. Import the API manupulation library.
2. Execute your test.

### Credits
This repo is the result of the collective knowledge of many people if you have any contribution, contact the owner of this repository.
People for this POQ exercise:
Carlos cotto, Angel Torre, Jhonatan Flores, Sebastian morales
