package com.griffithindustries.secrets.gateway;

import com.inductiveautomation.ignition.gateway.localdb.persistence.Category;
import com.inductiveautomation.ignition.gateway.localdb.persistence.EncodedStringField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.IdentityField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.localdb.persistence.StringField;
import com.inductiveautomation.ignition.gateway.web.components.editors.PasswordEditorSource;
import simpleorm.dataset.SFieldFlags;

public class SecretRecord extends PersistentRecord {
    public static final RecordMeta<SecretRecord> META =
        new RecordMeta<>(SecretRecord.class, "gi_secrets");

    public static final IdentityField Id = new IdentityField(META, "Id");

    public static final StringField Name = new StringField(META, "Name", SFieldFlags.SMANDATORY,
        SFieldFlags.SDESCRIPTIVE);

    public static final EncodedStringField Secret = new EncodedStringField(META, "Secret", SFieldFlags.SMANDATORY);

    static final Category Main = new Category("GriffithIndustries.Secrets", 0).include(Name, Secret);

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
