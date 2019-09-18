package be.abis.casebce.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class UtilityController implements Serializable {
	private static final long serialVersionUID = 1979783301951906737L;
	private String lang = "en";

	public void changeLanguage(String language) {
		UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();
		root.setLocale(new Locale(language));
		this.setLang(language);
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
