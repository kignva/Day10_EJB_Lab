/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs.feb2014.cs544.lab.ejb;

import edu.mum.cs.feb2014.cs544.lab.entity.Log;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author vyim
 */
@Stateless
public class myLogger {

    @Inject
    private LogService logService;

    public myLogger() {

    }

    @AroundInvoke
    private Object logMethod(InvocationContext ic) throws Exception {

        long startTime = System.currentTimeMillis();

        Object[] params = ic.getParameters();
        StringBuilder sb = new StringBuilder();
        sb.append(ic.getTarget().toString());
        sb.append(".");
        sb.append(ic.getMethod().getName());
        sb.append("(");
        if (null != params) {
            for (Object param : params) {
                sb.append(param.toString());

            }
        }
        sb.append(")");

        try {
            return ic.proceed();
        } finally {
//            log = new Log( ic.getTarget().toString() + " " +
//                     ic.getMethod().getName());
//            logService.addLog(log);
            long diffTime = System.currentTimeMillis() - startTime;

            sb.append(" took ");
            sb.append(diffTime);
            sb.append(" ms.");

            Log log = new Log(sb.toString());
            logService.addLog(log);
        }
    }

}
