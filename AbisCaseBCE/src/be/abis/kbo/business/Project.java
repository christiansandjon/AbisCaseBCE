package be.abis.kbo.business;

import java.util.*;

public class Project {

    private Company client;
    private double hourRate; // coup horaire
    private List<Manager> managers;

    public Project(Company client, double hourRate, List<Manager> managers) {
        this.client = client;
        this.hourRate = hourRate;
        this.managers = managers;
    }

    public Company getClient() {
        return client;
    }

    public void setClient(Company client) {
        this.client = client;
    }

    public double getHourRate() {
        return hourRate;
    }

    public void setHourRate(double hourRate) {
        this.hourRate = hourRate;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }
}
