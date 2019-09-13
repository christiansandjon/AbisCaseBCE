package be.abis.casebce.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.abis.casebce.model.ExternalWorker;
import be.abis.casebce.model.Worker;
import be.abis.casebce.model.WorkingDay;

@Named
@SessionScoped
public class WorkingDayController implements Serializable {
	@Inject
	@Named("worker")
	private Worker worker;
	@Inject
	private WorkingDay currentWorkingDay;

//	@EJB(name = "WorkerSession")
//	private WorkerSessionRemote workerSession;
//	@EJB(name = "WorkingDaySession")
//	private WorkingDaySessionRemote workingDaySession;

	@PostConstruct
	public void init() {
//		this.worker = this.workerSession.getUser();
//		if (this.isAvailable()) {
//			this.currentWorkingDay = this.workingDaySession.getCurrentWorkingDay((ExternalWorker) this.getWorker());
//		}
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

	public void startWorkingDay() {
//		this.currentWorkingDay = this.workingDaySession.startWorkingDay(this.currentWorkingDay);
	}

	public void closeWorkingDay() {
//		this.currentWorkingDay = this.workingDaySession.closeWorkingDay(this.currentWorkingDay);
	}

}
