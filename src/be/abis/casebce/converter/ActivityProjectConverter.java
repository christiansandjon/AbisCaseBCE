package be.abis.casebce.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import be.abis.casebce.controller.ActivityController;
import be.abis.casebce.model.Project;

@FacesConverter("activityProjectConverter")
public class ActivityProjectConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		ValueExpression vex = arg0.getApplication().getExpressionFactory().createValueExpression(arg0.getELContext(),"#{activityController}",ActivityController.class);
		ActivityController controller = (ActivityController) vex.getValue(arg0.getELContext());
		for (int i = 0; i < controller.getPotentialProjects().size(); i++) {
			if (controller.getPotentialProjects().get(i).getName().equalsIgnoreCase(arg2)) {
				return controller.getPotentialProjects().get(i);
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		Project project = (Project) arg2;
		return project.getName();
	}

}
