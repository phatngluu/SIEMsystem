package SIEMsystem.cep;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.*;

/**
 *  Class represents CEP engine of the system
 *  It takes configurations, initializes and activates its modules
 *  @author Luu Nguyen Phat
 */

@SuppressWarnings("rawtypes")
public class CEPEngine {
    private static CEPEngine cepEngine;
    private EPRuntime runtime;
    private Configuration configuration;
    private HashMap<Class, Long> eventCounter;
    private Properties properties = new Properties();
    private File configFile = new File(".resources/properties/cepengine.properties");

    /**
     * This method return a new instance of CEPEngine
     * @param configuration is the configuration to initialize the engine.
     * @return a new CEP engine
     */
    public static CEPEngine getNewInstance(Configuration configuration){
        cepEngine = new CEPEngine(configuration);
        return cepEngine;
    }

    /**
     * This method return a created instance of CEPEngine
     * @return a created CEP engine
     */
    public static CEPEngine getCreatedInstance(){
        if (cepEngine == null){
            System.out.println("Please execute CEPEngine.getNewInstance()");
        }
        return cepEngine;
    }

    /**
     * This method is used internally by getNewInstance method
     * @param configuration is the configuration to initialize the engine.
     */
    private CEPEngine(Configuration configuration){
        this.configuration = configuration;
        this.runtime = EPRuntimeProvider.getDefaultRuntime(this.configuration);
        this.eventCounter = new HashMap<Class, Long>();
        try {
            FileReader reader = new FileReader(configFile);
            properties.load(reader);
            reader.close();
        } catch (IOException ignore) {
            System.out.println("Cannot load property file: cepengine.properties");
        }
    }

    /**
     * This method return the runtime of the created engine.
     * The engine must be created before calling this method.
     * @return An EPRuntime instance. It can be used to send events to the engine.
     */
    public EPRuntime getRuntime(){
        return this.runtime;
    }

    /**
     * This method is used to compile and deploy EPL statements
     * It is used by the modules of the engine.
     * @return an EPStatement instance. It can be used to add a listener to statement.
     */
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

    /**
     * This method is used to active a module of the engine.
     * @param module is an instance of CEPEngine's module.
     */
    public void activate(Module module){
        module.activate(this);
    }

    /**
     * This method is about to get a property of the engine
     * @param PROP_NAME is the property name
     * @return a value of correspondng property name
     */
    public String getProperty(String PROP_NAME){
        return this.properties.getProperty(PROP_NAME);
    }

    /**
     * This method is about to set a property of the engine
     * @param PROP_NAME is the property name
     * @param VALUE is the property value
     */
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

    /**
     * This method is about to count number of each event type
     * Every time this method is called, the number of corresponding event type increase by 1
     * @param clazz event type
     */
    public void countEvent(Class clazz){
        if (eventCounter.containsKey(clazz)){
            eventCounter.replace(clazz, eventCounter.get(clazz) + 1);
        } else {
            eventCounter.put(clazz, (long) 1);
        }
    }

    /**
     * This method is about to get number of each event type
     * @param clazz event type
     * @return number of events for this event type. 0 if the event type is not registered.
     */
    public long getCountOfEvent(Class clazz){
        if (eventCounter.containsKey(clazz)){
            return eventCounter.get(clazz);
        } else {
            return (long) 0;
        }
    }
}