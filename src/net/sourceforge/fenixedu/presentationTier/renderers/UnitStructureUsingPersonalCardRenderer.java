package net.sourceforge.fenixedu.presentationTier.renderers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.renderers.components.HtmlComponent;
import net.sourceforge.fenixedu.renderers.contexts.PresentationContext;
import net.sourceforge.fenixedu.renderers.utils.RenderKit;
import net.sourceforge.fenixedu.renderers.utils.RenderMode;

public class UnitStructureUsingPersonalCardRenderer extends UnitStructureRenderer {

    private Map<String, String> properties = new HashMap<String,String>();
    
    public void setCardProperty(String name, String value) {
	properties.put(name, value);
    }
    
    public String getCardProperty(String name) {
	return properties.get(name);
    }
    
    private Properties getPropertyMap() {
	Properties properties = new Properties();
	for(String key : this.properties.keySet()) {
	    properties.put(key, this.properties.get(key));
	}
	return properties;
    }
    
    @Override
    protected HtmlComponent generatePerson(Person person) {
	    PresentationContext newContext = getContext().createSubContext(
			getContext().getMetaObject());
	    newContext.setProperties(getPropertyMap());
	    newContext.setRenderMode(RenderMode.getMode("output"));
	    
	    return RenderKit.getInstance().renderUsing(new PersonalCardRenderer(), newContext, person, Person.class);
    }
}
