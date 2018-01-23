package tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndexingTokenizer implements Tokenizer {
    
    public IndexingTokenizer() {
    }
    
    @Override
    public ArrayList<String> tokenize(String s) {
        
        ArrayList<String> ret = new ArrayList<String>();
        
        //keeping hyphens as one word
        Pattern p = Pattern.compile("(\\w-*)+"); 
        Matcher m = p.matcher(s);
        
        while (m.find()) {
            ret.add(m.group().toLowerCase()); //make everything to lower case
        }
        return ret;
    }
    
    //testing if this works
    public static void main(String args[]) {
        String st = "Fight erupts at Indianapolis McDonald's drive-thru over chicken nuggets order testing-hyphen erupts";
        IndexingTokenizer  token = new IndexingTokenizer();
        System.out.println(token.tokenize(st));
    }
    
}
