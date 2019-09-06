package be.abis.casebce.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.abis.casebce.model.ExternalWorker;
import be.abis.casebce.model.Worker;
import be.abis.casebce.model.WorkingDay;
import be.abis.casebce.session.WorkerSessionRemote;

@Named
@SessionScoped
public class WorkingDayController implements Serializable {
	@Inject
	@Named("worker")
	private Worker worker;
	@Inject
	private WorkingDay currentWorkingDay;

	@EJB(name = "WorkerSession")
	private WorkerSessionRemote workerSession;
	
	@PostConstruct
	public void init() {
		this.worker = this.workerSession.getUser();
		if (this.isAvailable()) {
			System.out.println("need to retrieve open working day or create a new one");
		}
	}
	
	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public WorkingDay getCurrentWorkingDay() {
		return currentWorkingDay;
	}
	public void setCurrentWorkingDay(WorkingDay currentWorkingDay) {
		this.currentWorkingDay = currentWorkingDay;
	}
	
	public boolean isAvailable() {
		return this.getWorker() instanceof ExternalWorker;
	}
	
}
