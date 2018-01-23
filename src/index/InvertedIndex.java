package index;

import io.DocSource;
import java.util.ArrayList;
import java.util.HashMap;
import score.TermScoringFun;
import tokenizer.Tokenizer;
import java.util.TreeSet;

public class InvertedIndex extends Index {
    
    private HashMap<String, HashMap<Integer, Integer>> _index;
    private HashMap<String, Integer> _docFreq;

    public InvertedIndex(DocSource doc_source, Tokenizer tokenizer, TermScoringFun scoring) {
        super(doc_source, tokenizer, scoring);
        _index = new HashMap<String, HashMap<Integer, Integer>>();
        _docFreq = new HashMap<String, Integer>();
    }
    
    @Override
    public int getDocumentFreq(String term) {
        return _docFreq.get(term);
    }

    @Override
    public ArrayList<DocScore> getSortedSearchResults(String query) {
        TreeSet<DocScore> set = new TreeSet<DocScore>();
        ArrayList<String> search = new ArrayList<String>(_tokenizer.tokenize(query));
        HashMap<Integer, Double> scoring = new HashMap<Integer, Double>();
        
        for (int k = 0; k < _docSource.getNumDocs(); k++) { //go through all docs
            for (int j = 0; j < search.size(); j++) { //go through all search queries
                  if (_index.containsKey(search.get(j)) && _index.get(search.get(j)).containsKey(k)) { //if the word is in the index
                        if (scoring.containsKey(k) && _index.get(search.get(j)).get(k) != null) { //if the doc is already in the scoring add it 
                            scoring.put(k, _scoring.scoreToken(search.get(j), _index.get(search.get(j)).get(k)) + scoring.get(k));
                        } 
                        else { //if the doc not in scoring add it to the 
                            scoring.put(k, _scoring.scoreToken(search.get(j), _index.get(search.get(j)).get(k)));
                        }
                }
            }
        }
        for (Integer i : scoring.keySet()) { //add all info into sorted set
            set.add(new SortedDocScore(scoring.get(i), i, _docSource.getDoc(i)));
        }
        //add it all to a treeset
        ArrayList<DocScore> ds = new ArrayList<DocScore>();
        ds.addAll(set);
        
        return ds;
    }

    @Override
    public void buildIndex() {
        ArrayList<String> words = new ArrayList<String>();

        for (int i = 0; i < _docSource.getNumDocs(); i++) { // go through all the files
            words.addAll(_tokenizer.tokenize(_docSource.getDoc(i))); //tokenize all the words in the file
            for (int j = 0; j < words.size(); j++) { //go through all the words
                if (_index.containsKey(words.get(j))) { //if the word is already in index
                    if (_index.get(words.get(j)).containsKey(i)) { //if second+ occurence of word in file
                        _index.get(words.get(j)).put(i, _index.get(words.get(j)).get(i) + 1);
                    } 
                    else {
                        _index.get(words.get(j)).put(i, 1); //if first occurence of word in a file
                        _index.put(words.get(j), _index.get(words.get(j))); //add the changed map to the index
                    }
                } 
                else if (_index.containsKey(words.get(j)) == false) { //if word not in index
                    HashMap<Integer, Integer> wordFreqNew = new HashMap<Integer, Integer>(); //make new hashmap
                    wordFreqNew.put(i, 1); // put freq of one in it
                    _index.put(words.get(j), wordFreqNew); //add it to the index
                }
                if (_index.get(words.get(j)) != null) //if the index has an entry
                    _docFreq.put(words.get(j), _index.get(words.get(j)).size()); //add the word and # of docs it is in to _docfreq
            }
            words.clear(); //clear the words for every new doc
        }
    }
    
    @Override
    public String toString() {
        return _index.toString();
    }
       
}