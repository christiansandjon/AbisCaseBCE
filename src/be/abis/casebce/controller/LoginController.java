package be.abis.casebce.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.abis.casebce.model.Login;
import be.abis.casebce.model.Worker;

@Named
@SessionScoped
public class LoginController implements Serializable {
	@Inject
	private Login login;
	@Inject
	@Named("worker")
	private Worker worker;
	
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
		return "";
	}
	
	public String logout() {
		return "";
	}
}
