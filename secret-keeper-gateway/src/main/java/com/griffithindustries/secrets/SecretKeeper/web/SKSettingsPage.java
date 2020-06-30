package com.griffithindustries.secrets.SecretKeeper.web;

import com.griffithindustries.secrets.SecretKeeper.records.SKSettingsRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.web.components.RecordActionTable;
import com.inductiveautomation.ignition.gateway.web.models.ConfigCategory;
import com.inductiveautomation.ignition.gateway.web.pages.IConfigPage;
import org.apache.commons.lang3.tuple.Pair;

/**
 * SKSettings extends {@link RecordActionTable} to use the gateway's built in UI for editing {@link PersistentRecord}s
 */
public class SKSettingsPage extends RecordActionTable<SKSettingsRecord> {
    public SKSettingsPage(IConfigPage configPage) {super(configPage); }

    @Override
    protected RecordMeta<SKSettingsRecord> getRecordMeta() {
        return SKSettingsRecord.META;
    }

    @Override
    public Pair<String, String> getMenuLocation() {
        return Pair.of(ConfigCategory.SECURITY.getName(), "Secret Keeper");
    }
}
