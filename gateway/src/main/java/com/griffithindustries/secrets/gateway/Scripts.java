package com.griffithindustries.secrets.gateway;

import com.inductiveautomation.ignition.common.script.PyArgParser;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import org.python.core.PyObject;
import simpleorm.dataset.SQuery;

public class Scripts {
    private final GatewayContext context;

    Scripts(GatewayContext context) {
        this.context = context;
    }

    public String getSecret(PyObject[] args, String[] keywords) {
        PyArgParser parsedArgs =
            PyArgParser.parseArgs(args, keywords, new String[]{"name"}, new Class[]{String.class}, "getSecret");
        String name = parsedArgs.requireString("name");
        SecretRecord secret =
            context.getPersistenceInterface().queryOne(new SQuery<>(SecretRecord.META).eq(SecretRecord.Name, name));
        if (secret != null) {
            return secret.getSecret();
        } else {
            return null;
        }
    }

}
