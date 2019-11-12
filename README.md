# Templates and Demonstrations for JavaFX
This repository contains barebones code samples and templates for JavaFX functionalities

## How to set Run Configurations for JavaFX project in Eclipse IDE

1. This software runs on version 13 of Java with the following external libraries:
..*javafx-sdk-13

2. Set up run configurations for .java class that contains "extends Application". In the "Run Configurations" menu, under the "Arguments" tab, insert the following code into VM arguments. Replace `/path/to/javafx-sdk-13` with the actual path to your JavaFX download. Also be sure to *uncheck* the box that says "Use the -XstartOnFirstThread argument when launching with SWT.
```
--module-path "/path/to/javafx-sdk-13/lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web
```
