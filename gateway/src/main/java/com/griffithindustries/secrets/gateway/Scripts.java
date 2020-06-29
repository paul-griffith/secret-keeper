package com.griffithindustries.secrets.gateway;

import java.util.Optional;

import com.inductiveautomation.ignition.common.script.PyArgParser;
import com.inductiveautomation.ignition.common.script.hints.ScriptFunction;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import org.python.core.PyObject;
import simpleorm.dataset.SQuery;

public class Scripts {
    private final GatewayContext context;

    Scripts(GatewayContext context) {
        this.context = context;
    }

    private Optional<String> getSecret(String name) {
        SecretRecord secret =
            context.getPersistenceInterface().queryOne(new SQuery<>(SecretRecord.META).eq(SecretRecord.Name, name));
        return Optional.ofNullable(secret).map(SecretRecord::getSecret);
    }

    @ScriptFunction(docBundlePrefix = "SecretKeeper")
    public String get(PyObject[] args, String[] keywords) {
        PyArgParser parsedArgs =
            PyArgParser.parseArgs(args, keywords, new String[]{"name"}, new Class[]{String.class}, "getSecret");
        String name = parsedArgs.requireString("name");
        return getSecret(name).orElse(null);
    }
}
