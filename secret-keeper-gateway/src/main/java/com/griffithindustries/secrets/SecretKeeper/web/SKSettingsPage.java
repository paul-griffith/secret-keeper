package com.griffithindustries.secrets.SecretKeeper.web;

import com.griffithindustries.secrets.SecretKeeper.records.SKSettingsRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.web.components.RecordActionTable;
import com.inductiveautomation.ignition.gateway.web.components.RecordEditForm;
import com.inductiveautomation.ignition.gateway.web.pages.IConfigPage;
import org.apache.commons.lang3.tuple.Pair;

import static com.griffithindustries.secrets.SecretKeeper.GatewayHook.CONFIG_CATEGORY;

/**
 * Filename: SKSettingsPage
 * Author: Garth Gross
 * Created on: 2020-06-29
 * Project: secret keeper
 *
 * SKSettings extends  {@link RecordEditForm} to provide a page where we can edit records in our PersistentRecord.
 */
public class SKSettingsPage extends RecordActionTable<SKSettingsRecord> {
    public SKSettingsPage(IConfigPage configPage) {super(configPage); }

    @Override
    protected RecordMeta<SKSettingsRecord> getRecordMeta() {
        return SKSettingsRecord.META;
    }

    @Override
    public Pair<String, String> getMenuLocation() {
        return Pair.of(CONFIG_CATEGORY.getName(), "Secret Keeper");
    }

}
