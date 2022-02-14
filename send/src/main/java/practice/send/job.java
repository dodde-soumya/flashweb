package practice.send;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.ValueProvider;

 

public class job {
	 public static void main(String[] args) {
         
         PipelineOptionsFactory.register(MyOptions.class);
         MyOptions options = PipelineOptionsFactory.fromArgs(args)
                                                         .withValidation()
                                                         .as(MyOptions.class);    
         Pipeline p = Pipeline.create(options);
         p.apply(TextIO.read().from(options.getinput()))
         .apply(TextIO.write().to(options.getoutput()).withoutSharding());
        
         p.run();
         }
     public interface MyOptions extends PipelineOptions {
         @Description("Input for the pipeline")
         @Default.String("schema.txt")
         ValueProvider<String> getinput() ;
         void setinput(ValueProvider<String> value);
         
         @Description("Output for the pipeline")
         @Default.String("output.txt")
         ValueProvider<String> getoutput() ;
      
         void setoutput(ValueProvider<String> value);
     }

}
