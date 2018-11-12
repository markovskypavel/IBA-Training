package by.iba.markovsky.festival.model.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlEnum
@XmlType(name = "activityType")
public enum ActivityType {
    @XmlEnumValue("FESTIVAL")
    FESTIVAL,
    @XmlEnumValue("CONCERT")
    CONCERT
}
