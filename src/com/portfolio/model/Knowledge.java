package com.portfolio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Knowledge {
	private String knowledgeId;
	private String knowledgeName;
	private String knowledgeCatId;
	private String knowledgeCreatedate;
	private String knowledgePath;
	private String knowledgeCreateName;
	private String knowledgeDesc;
	
	public String getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getKnowledgeName() {
		return knowledgeName;
	}
	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}
	public String getKnowledgeCatId() {
		return knowledgeCatId;
	}
	public void setKnowledgeCatId(String knowledgeCatId) {
		this.knowledgeCatId = knowledgeCatId;
	}
	public String getKnowledgeCreatedate() {
		return knowledgeCreatedate;
	}
	public void setKnowledgeCreatedate(String knowledgeCreatedate) {
		this.knowledgeCreatedate = knowledgeCreatedate;
	}
	public String getKnowledgePath() {
		return knowledgePath;
	}
	public void setKnowledgePath(String knowledgePath) {
		this.knowledgePath = knowledgePath;
	}
	public String getKnowledgeCreateName() {
		return knowledgeCreateName;
	}
	public void setKnowledgeCreateName(String knowledgeCreateName) {
		this.knowledgeCreateName = knowledgeCreateName;
	}
	public String getKnowledgeDesc() {
		return knowledgeDesc;
	}
	public void setKnowledgeDesc(String knowledgeDesc) {
		this.knowledgeDesc = knowledgeDesc;
	}
	
	
	
	
}
