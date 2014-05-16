package edu.mum.cs.feb2014.cs544.lab.mb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.mum.cs.feb2014.cs544.lab.ejb.LogService;
import edu.mum.cs.feb2014.cs544.lab.entity.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Va Y.
 */
@Named
@SessionScoped
public class LogMB implements Serializable {

    @EJB
    private LogService logService;

    private List<Log> logs = new ArrayList<>();

    final private int maxPerPage = 15;
    private int startPosition;

    public LogMB() {

    }

    @PostConstruct
    private void init() {
        loadData();
    }

    public String loadData() {
        logs = logService.getLogs(0, maxPerPage);

        return null;
    }

    public String nextData() {
        if (logService.count() - startPosition > 0) {
            startPosition += maxPerPage;
            logs = logService.getLogs(startPosition, maxPerPage);
            if (logs.isEmpty()) {
                startPosition -= maxPerPage;
                logs = logService.getLogs(startPosition, maxPerPage);
            }
        }
        return null;
    }

    public String previousData() {
        if (startPosition >= maxPerPage) {
            startPosition -= maxPerPage;
        } else {
            startPosition = 0;
        }

        logs = logService.getLogs(startPosition, maxPerPage);

        return null;
    }

    public List<Log> getLogs() {
        return logs;
    }

}
