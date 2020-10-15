package SIEMsystem.cep;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.*;

import SIEMsystem.alert.LoginAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.UnauthorizedEvent;


public class CEPEngine {
    private EPStatement statement;
    private EPRuntime runtime;
    private Configuration configuration;

    public CEPEngine() {
        this.configuration = new Configuration();
        this.configuration.getCommon().addEventType(AccessLogEvent.class);
        this.configuration.getCommon().addEventType(FailedLoginEvent.class);
        this.configuration.getCommon().addEventType(UnauthorizedEvent.class);
        this.configuration.getCommon().addEventType(LoginAlert.class);
        this.runtime = EPRuntimeProvider.getDefaultRuntime(this.configuration);

        WebserverSubEngine.activate(this);
        // PortscanSubEngine.activate(this);
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
}