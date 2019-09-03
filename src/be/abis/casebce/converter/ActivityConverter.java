package be.abis.casebce.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import be.abis.casebce.controller.ActivityController;
import be.abis.casebce.model.Activity;

@FacesConverter("activityConverter")
public class ActivityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		int hashCode = Integer.parseInt(arg2);
		ValueExpression vex = arg0.getApplication().getExpressionFactory().createValueExpression(arg0.getELContext(),
				"#{activityController}", ActivityController.class);
		ActivityController controller = (ActivityController) vex.getValue(arg0.getELContext());
		for (int i = 0; i < controller.getDisplayedActivities().size(); i++) {
			if (controller.getDisplayedActivities().get(i).hashCode() == hashCode) {
				return controller.getDisplayedActivities().get(i);
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		Activity activity = (Activity) arg2;
		return ((Integer) activity.hashCode()).toString();
	}

}
