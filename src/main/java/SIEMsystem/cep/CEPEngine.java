package SIEMsystem.cep;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.*;

public class CEPEngine {
    private EPRuntime runtime;
    private Configuration configuration;

    public CEPEngine(Configuration configuration){
        this.configuration = configuration;
        this.runtime = EPRuntimeProvider.getDefaultRuntime(this.configuration);
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

    public void activate(SubEngine subEngine){
        subEngine.activate(this);
    }
}