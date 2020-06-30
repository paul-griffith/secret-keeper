package com.griffithindustries.secrets.SecretKeeper.records;

import com.inductiveautomation.ignition.gateway.localdb.persistence.EncodedStringField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.IdentityField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.localdb.persistence.StringField;
import com.inductiveautomation.ignition.gateway.web.components.editors.PasswordEditorSource;
import simpleorm.dataset.SFieldFlags;

public class SKSettingsRecord extends PersistentRecord {
    public static final RecordMeta<SKSettingsRecord> META =
        new RecordMeta<>(SKSettingsRecord.class, "gi_secrets").setNounKey("SKSettingsRecord.Noun")
            .setNounPluralKey("SKSettingsRecord.Noun.Plural");

    public static final IdentityField Id = new IdentityField(META);

    public static final StringField Name = new StringField(META, "Name", SFieldFlags.SMANDATORY,
        SFieldFlags.SDESCRIPTIVE);

    public static final EncodedStringField Secret = new EncodedStringField(META, "Secret", SFieldFlags.SMANDATORY);

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
