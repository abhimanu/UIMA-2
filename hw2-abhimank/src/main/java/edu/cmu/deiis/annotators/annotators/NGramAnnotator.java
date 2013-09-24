/**
 * 
 */
package edu.cmu.deiis.annotators.annotators;

import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.NGram;
import edu.cmu.deiis.types.Token;

/**
 * @author abhimank
 *
 */
public class NGramAnnotator extends JCasAnnotator_ImplBase {

  /* (non-Javadoc)
   * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
   */
  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub

    FSIndex tokenIndex = jcas.getAnnotationIndex(Token.type);
    Iterator tokenIter = tokenIndex.iterator();   
    //    System.out.println("Hello World!");
    boolean restart = true;
    Token token1=null, token2=null, token3=null;
    while (tokenIter.hasNext()) {
      //      System.out.println("Hello World in while!");
      token1=token2;
      token2=token3;
      token3 = (Token)tokenIter.next();
      NGram unigram = new NGram(jcas);
      unigram.setBegin(token3.getBegin());
      unigram.setEnd(token3.getEnd());
      unigram.addToIndexes();
//      System.out.print(" "+token3.getEnd() + " " + token3.getBegin()+" token3: "+token3.getCoveredText());      
      
      if(token2!=null){
        NGram bigram = new NGram(jcas);
        bigram.setBegin(token2.getBegin());
        bigram.setEnd(token3.getEnd());
        bigram.addToIndexes();
//        System.out.print(" token2: "+token2.getCoveredText());
      }
      if(token1!=null){
        NGram trigram = new NGram(jcas);
        trigram.setBegin(token1.getBegin());
        trigram.setEnd(token3.getEnd());
        trigram.addToIndexes();
//        System.out.println(" token1: "+token1.getCoveredText());
      }
      if(token3.getCoveredText().contains("?")||token3.getCoveredText().contains(".")){
//        System.out.println("Found the mofo, "+token3.getCoveredText());
        token1=null;
        token2=null;
        token3=null;
      }
    }
    //    System.out.println("Hello World after while!");
  }
}

