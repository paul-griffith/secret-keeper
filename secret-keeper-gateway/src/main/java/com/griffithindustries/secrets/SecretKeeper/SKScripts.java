package com.griffithindustries.secrets.SecretKeeper;

import com.inductiveautomation.ignition.common.script.PyArgParser;
import com.griffithindustries.secrets.SecretKeeper.records.SKSettingsRecord;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import org.python.core.PyObject;
import simpleorm.dataset.SQuery;

/**
 * Filename: SKScripts
 * Author: Garth Gross
 * Created on: 2020-06-29
 * Project: secret keeper
 */

public class SKScripts {
    private final GatewayContext context;

    SKScripts(GatewayContext context) {
        this.context = context;
    }

    public String getSecret(PyObject[] args, String[] keywords) {
        PyArgParser parsedArgs =
            PyArgParser.parseArgs(args, keywords, new String[]{"name"}, new Class[]{String.class}, "getSecret");
        String name = parsedArgs.requireString("name");
        SKSettingsRecord secret =
            context.getPersistenceInterface().queryOne(new SQuery<>(SKSettingsRecord.META).eq(SKSettingsRecord.Name, name));
        if (secret != null) {
            return secret.getSecret();
        } else {
            return null;
        }
    }
}
