import java.io.IOException;
import java.util.*;
import java.lang.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;
public class pgMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        double rank = Double.parseDouble(words[words.length - 1]); // page rank value
        int outNums = words.length - 2; // the number of outlinks, starts from 1 to words.length - 2
        for (int i = 1; i < words.length - 1; i++) {
            String resKey = words[i];
            String resValue = words[0] + " " + rank / outNums;
            context.write(new Text(resKey), new Text(resValue));
        }
        StringBuilder out = new StringBuilder();
        for (int i = 1; i < words.length - 1; i++) {
            out.append(words[i] + " ");
        }
        // System.out.println(words[0] + out.toString() + "\n");
        context.write(new Text(words[0]), new Text(out.toString()));
    }
}
