package com.griffithindustries.secrets.SecretKeeper.records;

import com.inductiveautomation.ignition.gateway.localdb.persistence.Category;
import com.inductiveautomation.ignition.gateway.localdb.persistence.EncodedStringField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.IdentityField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.localdb.persistence.StringField;
import com.inductiveautomation.ignition.gateway.web.components.editors.PasswordEditorSource;
import simpleorm.dataset.SFieldFlags;

/**
 * Filename: SKSettingsRecord
 * Author: Garth Gross
 * Created on: 2020-06-29
 * Project: secret keeper
 */
public class SKSettingsRecord extends PersistentRecord {

    public static final RecordMeta<SKSettingsRecord> META = new RecordMeta<SKSettingsRecord>(
            SKSettingsRecord.class, "gi_secrets")
                .setNounKey("SKSettingsRecord.Noun")
                .setNounPluralKey("SKSettingsRecord.Noun.Plural");

    public static final IdentityField Id = new IdentityField(META);

    public static final StringField Name = new StringField(META, "Name", SFieldFlags.SMANDATORY,
        SFieldFlags.SDESCRIPTIVE);

    public static final EncodedStringField Secret = new EncodedStringField(META, "Secret", SFieldFlags.SMANDATORY);

    // create categories for our record entries, getting titles from the SKSettingsRecord.properties, and
    // ordering through integer ranking
    static final Category Secrets = new Category("SKSettingsRecord.Category.Secret", 1000).include(Name, Secret);

    static {
        Secret.getFormMeta().setEditorSource(PasswordEditorSource.getSharedInstance());
    }

    @Override
    public RecordMeta<?> getMeta() {
        return META;
    }

    public String getSecret() {
        return getString(Secret);
    }
}
