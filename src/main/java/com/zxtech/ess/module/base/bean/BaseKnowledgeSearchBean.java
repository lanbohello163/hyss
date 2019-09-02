package com.zxtech.ess.module.base.bean;

import com.zxtech.platform.utils.page.PageParameter;

/*
 * @version 3.0
 */

public class BaseKnowledgeSearchBean extends PageParameter {

	private static final long serialVersionUID = 1L;
	
	private String knowledge_question_category;
	
	private String knowledge_question_child_category;
	
	private String question_desc;

	public String getKnowledge_question_category() {
		return knowledge_question_category;
	}

	public void setKnowledge_question_category(String knowledge_question_category) {
		this.knowledge_question_category = knowledge_question_category;
	}

	public String getKnowledge_question_child_category() {
		return knowledge_question_child_category;
	}

	public void setKnowledge_question_child_category(String knowledge_question_child_category) {
		this.knowledge_question_child_category = knowledge_question_child_category;
	}

	public String getQuestion_desc() {
		return question_desc;
	}

	public void setQuestion_desc(String question_desc) {
		this.question_desc = question_desc;
	}


	
	
	
	
}