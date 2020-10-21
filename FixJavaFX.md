Add this in file `.vscode/launch.json`:

   "vmArgs": "--module-path /usr/lib/jvm/javafx-arm-sdk/lib --add-modules javafx.controls,javafx.fxml",


STILL NOT WORK? Add this in file `.classpath`:

	<classpathentry kind="lib" path="/usr/lib/jvm/javafx-arm-sdk/lib/javafx.base.jar"/>
	<classpathentry kind="lib" path="/usr/lib/jvm/javafx-arm-sdk/lib/javafx.graphics.jar"/>
	<classpathentry kind="lib" path="/usr/lib/jvm/javafx-arm-sdk/lib/javafx.controls.jar"/>
	<classpathentry kind="lib" path="/usr/lib/jvm/javafx-arm-sdk/lib/javafx.fxml.jar"/>
	<classpathentry kind="src" output="target/test-classes" path="target/generated-test-sources/test-annotations">

