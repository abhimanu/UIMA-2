/**
 * 
 */
package edu.cmu.deiis.annotators.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Answer;

/**
 * @author abhimank
 *
 */
public class AnswerAnnotator extends JCasAnnotator_ImplBase {

  /* (non-Javadoc)
   * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
   */
  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    int prevIndex = 0; //Answer 
    for(String line: jcas.getDocumentText().split("\\n")){
      if(prevIndex == 0){
        prevIndex += (line.length()+1);
        continue;
      }
      Answer answer = new Answer(jcas);
      answer.setBegin(prevIndex+4);
      answer.setEnd(prevIndex+line.length());
      prevIndex += line.length()+1;
      int score = new Integer(line.substring(2, 3));
      answer.setIsCorrect(score==1);
      answer.addToIndexes();
    }
  }

}
