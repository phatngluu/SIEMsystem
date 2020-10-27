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