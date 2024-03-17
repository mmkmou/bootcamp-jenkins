package xyz.mmkmou.bootcamp.transactions.api.models.transactions;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum SourceEnum {
    FIELDS("boutique"),
    MOB_APP("mobile"),
    WEB_APP("web");

    //Lookup table
    private static final Map<String, SourceEnum> lookup = new HashMap<>();

    public final String label;

    @JsonValue
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static SourceEnum valueOfLabel(String label) {
        return lookup.get(label);
    }
    static
    {
        for(SourceEnum e : values())
        {
            lookup.put(e.getLabel(), e);
        }
    }
}
