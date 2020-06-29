package com.griffithindustries.secrets.gateway;

import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.web.components.RecordActionTable;
import com.inductiveautomation.ignition.gateway.web.pages.IConfigPage;
import org.apache.commons.lang3.tuple.Pair;

import static com.inductiveautomation.ignition.gateway.web.models.ConfigCategory.SECURITY;

public class SecretManagerPage extends RecordActionTable<SecretRecord> {
    public SecretManagerPage(IConfigPage configPage) {
        super(configPage);
    }

    @Override
    protected RecordMeta<SecretRecord> getRecordMeta() {
        return SecretRecord.META;
    }

    @Override
    public Pair<String, String> getMenuLocation() {
        return Pair.of(SECURITY.getName(), "secrets");
    }
}
