package be.abis.casebce.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class WorkingDay implements Serializable {

	private int id;
	private LocalDateTime start;
	private LocalDateTime end;

	@Inject
	private ExternalWorker worker;

	// constructor
	public WorkingDay() {
	}

	// getter and setters
	public int getId() {
		return id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public ExternalWorker getWorker() {
		return worker;
	}

	public void setWorker(ExternalWorker worker) {
		this.worker = worker;
	}

	public long getStartTimeInSeconds() {
		return this.start.getLong(ChronoField.SECOND_OF_DAY);
	}
}
