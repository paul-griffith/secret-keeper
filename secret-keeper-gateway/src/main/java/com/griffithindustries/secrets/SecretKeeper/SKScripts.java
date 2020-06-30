package com.griffithindustries.secrets.SecretKeeper;

import java.util.Optional;

import com.inductiveautomation.ignition.common.script.PyArgParser;
import com.griffithindustries.secrets.SecretKeeper.records.SKSettingsRecord;
import com.inductiveautomation.ignition.common.script.ScriptManager;
import com.inductiveautomation.ignition.common.script.hints.ScriptFunction;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import org.python.core.PyObject;
import simpleorm.dataset.SQuery;

/**
 * A simple POJO that will be exposed to script authors by Ignition; see
 * {@link ScriptManager#addScriptModule(String, Object)} and the various overloads.
 */
public class SKScripts {
    private final GatewayContext context;

    SKScripts(GatewayContext context) {
        this.context = context;
    }

    private Optional<String> getSecret(String name) {
        SKSettingsRecord secret =
            context.getPersistenceInterface().queryOne(new SQuery<>(SKSettingsRecord.META).eq(SKSettingsRecord.Name, name));
        return Optional.ofNullable(secret).map(SKSettingsRecord::getSecret);
    }

    /**
     * The function accepts the two arrays so that it can be invoked from Jython scripting with keyword arguments.
     * Ignition offers the {@link PyArgParser} class to make it easier to parse these two parameters into useful
     * arguments.
     */
    @ScriptFunction(docBundlePrefix = "SecretKeeper")
    public String get(PyObject[] args, String[] keywords) {
        PyArgParser parsedArgs =
            PyArgParser.parseArgs(args, keywords, new String[]{"name"}, new Class[]{String.class}, "getSecret");
        String name = parsedArgs.requireString("name");
        return getSecret(name).orElse(null);
    }
}
