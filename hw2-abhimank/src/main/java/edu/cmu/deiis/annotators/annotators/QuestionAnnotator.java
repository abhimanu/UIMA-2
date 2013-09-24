/**
 * 
 */
package edu.cmu.deiis.annotators.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Question;

/**
 * @author abhimank
 *
 */
public class QuestionAnnotator extends JCasAnnotator_ImplBase {

  /* (non-Javadoc)
   * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
   */
  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    String line = jcas.getDocumentText().split("\\n")[0];
    Question question = new Question(jcas);
    question.setBegin(2);
    question.setEnd(line.length());
    question.addToIndexes();
  }

}
