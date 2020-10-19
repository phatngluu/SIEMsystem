package SIEMsystem.cep;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.*;

public class CEPEngine {
    private static CEPEngine cepEngine;
    private EPRuntime runtime;
    private Configuration configuration;
    private Properties properties = new Properties();
    private File configFile = new File(".resources/properties/cepengine.properties");

    public static CEPEngine getNewInstance(Configuration configuration){
        cepEngine = new CEPEngine(configuration);
        return cepEngine;
    }

    public static CEPEngine getCreatedInstance(){
        if (cepEngine == null){
            System.out.println("Please execute CEPEngine.getNewInstance()");
        }
        return cepEngine;
    }
    private CEPEngine(Configuration configuration){
        this.configuration = configuration;
        this.runtime = EPRuntimeProvider.getDefaultRuntime(this.configuration);
        try {
            FileReader reader = new FileReader(configFile);
            properties.load(reader);
            reader.close();
        } catch (IOException ignore) {
            System.out.println("Cannot load property file: cepengine.properties");
        }
    }

    public EPRuntime getRuntime(){
        return this.runtime;
    }

    public EPStatement compileAndDeploy(String epl) {
        try {
            CompilerArguments args = new CompilerArguments(this.configuration);
            args.getPath().add(this.runtime.getRuntimePath());
            System.out.print("\nCompiling... ");
            EPCompiled compiled = EPCompilerProvider.getCompiler().compile(epl, args);
            System.out.print("OK");
            System.out.print("\tDeploying...");
            EPDeployment epDeployment = this.runtime.getDeploymentService().deploy(compiled);
            System.out.print("OK");
            return epDeployment.getStatements()[0];
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void activate(Module module){
        module.activate(this);
    }

    public String getProperty(String PROP_NAME){
        return this.properties.getProperty(PROP_NAME);
    }

    public void setProperty(String PROP_NAME, String VALUE){
        try {
            FileOutputStream out = new FileOutputStream(configFile);
            this.properties.setProperty(PROP_NAME, VALUE);
            this.properties.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}