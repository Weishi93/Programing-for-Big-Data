import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;
public class wc {
	public static void main(String[] args) throws Exception { 
		if (args.length != 2) {
      		System.err.println("Usage: Word Count <input path> <output path>");
      		System.exit(-1);
    	}
		Job job = new Job(); 
		job.setJarByClass(wc.class); 
		job.setJobName("Word Count");
		FileInputFormat.addInputPath(job, new Path(args[0])); 
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    job.setMapperClass(wcMapper.class);
		job.setReducerClass(wcReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

