package be.abis.casebce.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("hourConverter")
public class HourConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		LocalDateTime date = null;
		try {
			date = LocalDateTime.parse(arg2, DateTimeFormatter.ofPattern("kk:mm"));
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Invalid hour");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(message);
		}
		return date;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		return ((LocalDateTime) arg2).format(DateTimeFormatter.ofPattern("kk:mm"));
	}

}
