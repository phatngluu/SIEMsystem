package SIEMsystem.cep;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.*;


public class CEPEngine {
    private EPStatement statement;
    private EPRuntime runtime;
    private Configuration configuration;

    // Step 0
    public void addEventType(Class eventType){
        if (this.configuration == null){
            this.configuration = new Configuration();
        }
        this.configuration.getCommon().addEventType(eventType);
    }

    // Step 1
    public void initRuntime(){
        this.runtime = EPRuntimeProvider.getDefaultRuntime(this.configuration); 
    }

    // Step 2
    public void compileAndDeploy(String name, String epl, Class eventType) {
        System.out.print("Class: " + eventType.getSimpleName());
        CompilerArguments compilerArguments = new CompilerArguments(this.configuration);

        // Compile
        System.out.print("\tCompiling... ");
        EPCompiler compiler = EPCompilerProvider.getCompiler();
        try {
            EPCompiled epCompiled = compiler.compile("@name('" + name + "') " + epl, compilerArguments);
            System.out.print("OK");
            // Deploy
            System.out.print("\tDeploying...");
            EPDeployment epDeployment;
            try {
                epDeployment = runtime.getDeploymentService().deploy(epCompiled);
                System.out.print("OK");
                EPStatement statement = runtime.getDeploymentService().getStatement(epDeployment.getDeploymentId(), name);
                this.statement = statement;
            } catch (EPDeployException ex) {
                System.out.print("FAILED");
                ex.printStackTrace();
            }
            
        } catch (EPCompileException ex) {
            System.out.print("FAILED");
            ex.printStackTrace();
        }
        System.out.println("");
    }

    // Step 3
    public void addListener(UpdateListener listener) {
        this.statement.addListener(listener);
    }

    // Step 4
    public EPRuntime getRuntime(){
        return this.runtime;
    }
}