package be.abis.casebce.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import be.abis.casebce.model.Login;
import be.abis.casebce.model.Worker;
import be.abis.casebce.service.WorkerService;

@Named
@SessionScoped
public class LoginController implements Serializable {
	@Inject
	private Login login;
	@Inject
	@Named("worker")
	private Worker worker;
	private boolean loginIsOk = false;

	private WorkerService service = new WorkerService();

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String login() {
		try {
			this.worker = this.service.login(this.login);
		} catch (Exception e) {
			System.out.println(e.toString());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Issue", e.getMessage()));
			return "login";
		}
		this.loginIsOk = true;
		return "activitydisplay?faces-redirect=true";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}

	public void checkLogin() {
		if (!this.loginIsOk) {
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("login?faces-redirect=true");
		}
	}
}
