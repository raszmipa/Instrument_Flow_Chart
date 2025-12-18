package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import java.util.Collection;

public class DroolsLogic {

    private KieSession kSession;

    public DroolsLogic() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        this.kSession = kc.newKieSession("ksession-rules");
    }

    public void fireRules() {
        kSession.fireAllRules();
    }

    public InstrumentCandidate getRecommendedInstrument() {
        Collection<InstrumentCandidate> instruments = 
            (Collection<InstrumentCandidate>) kSession.getObjects(o -> o instanceof InstrumentCandidate);
        
        if (!instruments.isEmpty()) {
            return instruments.iterator().next();
        }
        return null;
    }

    public Question getCurrentQuestion() {
        Collection<Question> questions = 
            (Collection<Question>) kSession.getObjects(o -> o instanceof Question);
        
        if (!questions.isEmpty()) {
            return questions.iterator().next();
        }
        return null;
    }

    public void submitAnswer(Question question, String chosenAnswer) {
        FactHandle qHandle = kSession.getFactHandle(question);
        if (qHandle != null) {
            kSession.delete(qHandle);
        }

        
        Answer answer = new Answer(question.getQuestionId(), chosenAnswer);
        kSession.insert(answer);

        kSession.fireAllRules();
    }

    public void close() {
        if (kSession != null) {
            kSession.dispose();
        }
    }
}