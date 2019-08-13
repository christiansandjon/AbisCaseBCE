package be.abis.kbo.business;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

public class WorkingDay {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Employee concernedEmployee;

	private static DateTimeFormatter defaultTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm");
	private static DateTimeFormatter defaultDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public WorkingDay(Employee concernedEmployee) {
		this(concernedEmployee, LocalDateTime.now());
	}

	public WorkingDay(Employee concernedEmployee, LocalDateTime startTime, LocalDateTime endTime) {
		this(concernedEmployee, startTime);
		this.endTime = endTime;
	}

	public WorkingDay(Employee concernedEmployee, LocalDateTime startTime) {
		this.concernedEmployee = concernedEmployee;
		this.startTime = startTime;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public Employee getConcernedEmployee() {
		return concernedEmployee;
	}

	public void closeWorkingDay() {
		closeWorkingDay(LocalDateTime.now());
	}

	public void closeWorkingDay(LocalDateTime closingTime) {
		if (this.getEndTime() == null) {
			if (this.getStartTime().isBefore(closingTime)) {
				if (this.getStartTime().getYear() == closingTime.getYear()
						&& this.getStartTime().getDayOfYear() == closingTime.getDayOfYear()) {
					this.endTime = closingTime;
				} else {
					System.out.println("Impossible to close the " + closingTime.format(defaultDateFormat)
							+ " a working day started the " + this.getStartTime().format(defaultDateFormat));
				}
			} else {
				System.out.println("A working day can't be closed a time anterior from its start");
			}
		} else {
			System.out.println("Working day already closed the " + this.getEndTime().format(defaultTimeFormat));
		}
	}

	public String generateJSon() throws IOException {
		JSONObject json = new JSONObject();
		json.put("employeeID", this.concernedEmployee.getId());
		json.put("startTime", this.getStartTime().format(defaultTimeFormat));
		if (this.getEndTime() != null) {
			json.put("endTime", this.getEndTime().format(defaultTimeFormat));
		}
		StringWriter out = new StringWriter();
		json.writeJSONString(out);
		return out.toString();
	}
}
