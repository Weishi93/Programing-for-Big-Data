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
public class pgReducer extends Reducer<Text, Text, NullWritable, Text> {
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		double sum = 0; 
		StringBuilder outLinks = new StringBuilder();
		for (Text value : values) {
			String[] words = value.toString().split(" ");
			try {

					sum += Double.parseDouble(words[words.length - 1]);
			} catch (NumberFormatException e) {
				for (String str : words) {
					outLinks.append(str + " ");
				}
			}
	    }
	    StringBuilder res = new StringBuilder();
	    res.append(key.toString() + " ");
	    res.append(outLinks);
	    res.append(sum);
		context.write(NullWritable.get(), new Text(res.toString())); 
	}
}
