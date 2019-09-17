package be.abis.casebce.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import be.abis.casebce.model.ExternalWorker;
import be.abis.casebce.model.Worker;
import be.abis.casebce.model.WorkingDay;
import be.abis.casebce.service.WorkerService;
import be.abis.casebce.service.WorkingDayService;

@Named
@SessionScoped
public class WorkingDayController implements Serializable {
	@Inject
	@Named("worker")
	private Worker worker;
	@Inject
	private WorkingDay currentWorkingDay;

	private WorkerService workerService = new WorkerService();
	private WorkingDayService workingDayService = new WorkingDayService();

	@PostConstruct
	public void init() {
		ValueExpression vex = FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
				.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{loginController}",
						LoginController.class);
		LoginController controller = (LoginController) vex.getValue(FacesContext.getCurrentInstance().getELContext());
		this.worker = controller.getWorker();
		if (this.isAvailable()) {
			try {
				this.currentWorkingDay = this.workingDayService.getCurrentWorkingDay(this.worker.getId());
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			}
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

	public void startWorkingDay() {
		try {
			this.currentWorkingDay = this.workingDayService.startWorkingDay(this.currentWorkingDay);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public void closeWorkingDay() {
		try {
			this.currentWorkingDay = this.workingDayService.closeWorkingDay(this.currentWorkingDay);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

}
