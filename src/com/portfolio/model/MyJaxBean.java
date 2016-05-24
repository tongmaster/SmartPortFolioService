package com.portfolio.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyJaxBean {
    @XmlElement public String param1;
    @XmlElement public String param2;
}
