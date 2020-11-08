# SIEM.Systems
A SIEM Systems in the purpose of "CS Project" subject at VGU
# Work organization
- Communication channel: Messenger, sharing documents
- Meeting Plan: 
  - Monday morning: announce tasks of this week
  - Friday afternoon: Sprint Review/Retrospective
  - Others: asynchronus
- Code collab: Github
- Scrum Plan: Google Sheets
# [Open Scrum Planning](https://docs.google.com/spreadsheets/d/18XOCYiPlReWLvm8figp2Bqw22co1El8d6yE1n-eLEi8/edit?usp=sharing)

# Notes on bug fixes:
## JavaFX
Add this in file `.vscode/launch.json` below `launch`:
```
"vmArgs": "--module-path /usr/lib/jvm/javafx-arm-sdk/lib --add-modules javafx.controls,javafx.fxml",
```

## Produce JAR
Put this in build/plugins section:

```xml
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <configuration>
    <archive>
        <manifest>
        <mainClass>SIEMsystem.dashboard.Main</mainClass>
        </manifest>
    </archive>
    <descriptorRefs>
        <descriptorRef>jar-with-dependencies</descriptorRef>
    </descriptorRefs>
    </configuration>
</plugin>
```

Open terminal, at project directory, enter:

```bash
mvn clean compile assembly:single
```

To run the system, please make sure your system folder contains as following:

```
- folder\
    - .resource\properties
        - cepengine.properties
        - priorities.properties
    - SIEMsystem*.jar
    - run.sh
```

In that:

- `.resource`: for storing configurations
- `SIEMsystem*.jar`: jar file of the system
- `run.sh`: script to run the system