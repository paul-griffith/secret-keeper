package com.griffithindustries.secrets.SecretKeeper;

import java.sql.SQLException;
import java.util.List;

import com.griffithindustries.secrets.SecretKeeper.web.SKSettingsPage;
import com.inductiveautomation.ignition.common.BundleUtil;
import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.common.script.ScriptManager;
import com.inductiveautomation.ignition.common.script.hints.PropertiesFileDocProvider;
import com.inductiveautomation.ignition.common.util.LoggerEx;
import com.griffithindustries.secrets.SecretKeeper.records.SKSettingsRecord;
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import com.inductiveautomation.ignition.gateway.web.components.ConfigPanel;
import com.inductiveautomation.ignition.gateway.web.models.ConfigCategory;
import com.inductiveautomation.ignition.gateway.web.models.DefaultConfigTab;
import com.inductiveautomation.ignition.gateway.web.models.IConfigTab;

/**
 * The "gateway hook" is the entry point for a module on the gateway.
 *
 * This example uses the new status and config pages for 7.9 and later.
 */
public class GatewayHook extends AbstractGatewayModuleHook {
    private static final LoggerEx LOG = LoggerEx.newBuilder().build("SecretKeeper");

    /**
     * An IConfigTab contains all the info necessary to create a link to your config page on the gateway nav menu.
     * In order to make sure the breadcrumb and navigation works properly, the 'name' field should line up
     * with the right-hand value returned from {@link ConfigPanel#getMenuLocation}. In this case name("Secret Keeper")
     * lines up with SKSettingsPage#getMenuLocation().getRight()
     */
    public static final IConfigTab SK_CONFIG_ENTRY = DefaultConfigTab.builder()
            .category(ConfigCategory.SECURITY)
            .name("Secret Keeper")
            .i18n("SecretKeeper.nav.settings.title")
            .page(SKSettingsPage.class)
            .terms("secret", "keeper", "password", "api", "key")
            .build();

    private GatewayContext context;

    @Override
    public void setup(GatewayContext gatewayContext) {
        this.context = gatewayContext;

        LOG.debug("Beginning setup of SecretKeeper module");

        // Register GatewayHook.properties by registering the GatewayHook.class with BundleUtils
        BundleUtil.get().addBundle("SecretKeeper", getClass(), "SecretKeeper");

        // Verify tables for persistent records if necessary
        verifySchema();

        LOG.debug("SecretKeeper setup completed");
    }

    private void verifySchema() {
        try {
            context.getSchemaUpdater().updatePersistentRecords(SKSettingsRecord.META);
        } catch (SQLException e) {
            LOG.error("Unable to create required internal DB table", e);
        }
    }

    @Override
    public void startup(LicenseState activationState) {
        //no-op
    }

    @Override
    public void shutdown() {
        /* remove our bundle */
        BundleUtil.get().removeBundle("SecretKeeper");
    }

    @Override
    public List<? extends IConfigTab> getConfigPanels() {
        return List.of(SK_CONFIG_ENTRY);
    }

    @Override
    public void initializeScriptManager(ScriptManager manager) {
        manager.addScriptModule("system.secrets", new SKScripts(context), new PropertiesFileDocProvider());
    }

    @Override
    public boolean isFreeModule() {
        return true;
    }

    @Override
    public boolean isMakerEditionCompatible() {
        return true;
    }
}
