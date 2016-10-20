import java.io.*;
import java.util.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;
public class pg {
	public static void main(String[] args) throws Exception { 
		if (args.length != 2) {
      		System.err.println("Usage: Word Count <input path> <output path>");
      		System.exit(-1);
    	}
		String inputPath = args[0], outputPath = args[1];
		for (int i = 0; i < 3; i++) {
			Job job = new Job(); 
			job.setJarByClass(pg.class); 
			job.setJobName("Page Rank");
			job.setNumReduceTasks(1);
			FileInputFormat.addInputPath(job, new Path(inputPath)); 
			FileOutputFormat.setOutputPath(job, new Path(outputPath));
		    job.setMapperClass(pgMapper.class);
			job.setReducerClass(pgReducer.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputKeyClass(Text.class);
			job.setOutputKeyClass(NullWritable.class);
			job.setOutputValueClass(Text.class);		
			inputPath = outputPath + "part-r-00000";
			outputPath = "class3/output" + Integer.toString(i + 1) + "/";
			job.waitForCompletion(true);
			// System.exit(job.waitForCompletion(true) ? 0 : 1);
		}
		System.exit(0);
	}
}

