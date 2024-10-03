package jsc;

import jsc.scenario.JSCColorScenario;
import jsc.scenario.JSCDefaultScenario;
import jsc.scenario.JSCHelpScenario;
import jsc.scenario.JSCSketchSideScenario;
import jsc.scenario.JSCSketchUpperScenario;
import x.XScenarioMgr;

public class JSCScenarioMgr extends XScenarioMgr {
    // constructor
    public JSCScenarioMgr(JSC jsc) {
        super(jsc);   
    }

    @Override
    protected void addScenarios() {
        this.addScenario(
            JSCDefaultScenario.createSingleton(this.mApp));
        this.addScenario(
            JSCSketchUpperScenario.createSingleton(this.mApp));
        this.addScenario(
            JSCSketchSideScenario.createSingleton(this.mApp));
        this.addScenario(JSCColorScenario.createSingleton(this.mApp));
        this.addScenario(JSCHelpScenario.createSingleton(this.mApp));
    }

    @Override
    protected void setInitCurScene() {
        this.setCurScene(
            JSCDefaultScenario.CakeUpperReadyScene.getSingleton());
        
    }
    
}
