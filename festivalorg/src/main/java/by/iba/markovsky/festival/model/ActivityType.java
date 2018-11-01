package by.iba.markovsky.festival.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "activityType")
@XmlEnum
public enum ActivityType {
    @XmlEnumValue("FESTIVAL")
    FESTIVAL,
    @XmlEnumValue("CONCERT")
    CONCERT
}
