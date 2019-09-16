package be.abis.casebce.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
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
		this.worker = this.service.login(this.login);
		return "";
	}
	
	public String logout() {
		return "";
	}
}
