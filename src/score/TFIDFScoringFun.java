package score;

import index.Index;
import io.DocSource;
import java.lang.Math;

public class TFIDFScoringFun implements TermScoringFun {
    
    DocSource _docSource;
    Index _index;
    
    @Override
    public void init(Index s) {
        _docSource = s.getDocSource();
        _index = s;
    }

    @Override
    public double scoreToken(String term, int freq) {
        double score = 0;
        
        try { //just copy the formula from the projec doc
            score = Math.log10(((double)_docSource.getNumDocs() / (double)_index.getDocumentFreq(term))) * Math.log10(1.0 + (double)freq);
        } catch (Exception ex) {
            System.out.println("logging something bad");
        }
        return score;
    }   
}