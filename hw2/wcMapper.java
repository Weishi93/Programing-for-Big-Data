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
public class wcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split("\\W");
        Map<String, Integer> count = new HashMap<>();
        count.put("dec", 0);
        count.put("java", 0);
        count.put("hackathon", 0);
        count.put("chicago", 0);
        for (String word : words) {
            String tmp = word.toLowerCase();
            if (count.containsKey(tmp)) {
                count.put(tmp, count.get(tmp) + 1);
            }
        }
        Iterator it = count.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String resKey = (String)pair.getKey();
            int resValue = (int)pair.getValue();
            context.write(new Text(resKey), new IntWritable(resValue));
        }
    }
}
