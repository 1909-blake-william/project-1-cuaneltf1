package com.fdesign.models;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimbursement_id;
	private int reimbursement_amount;
	private Timestamp reimbursement_submitted;
	private Timestamp reimbursement_resolved;
	private String reimbursement_description;
	private String reimbursement_resolution;
	private int reimbursement_author;
	private int reimbursement_resolver;
	private int reimbursement_status_id;
	private int reimbursement_type_id;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimbursement_id, int reimbursement_amount, Timestamp reimbursement_submitted,
			Timestamp reimbursement_resolved, String reimbursement_description, String reimbursement_resolution,
			int reimbursement_author, int reimbursement_resolver, int reimbursement_status_id,
			int reimbursement_type_id) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.reimbursement_amount = reimbursement_amount;
		this.reimbursement_submitted = reimbursement_submitted;
		this.reimbursement_resolved = reimbursement_resolved;
		this.reimbursement_description = reimbursement_description;
		this.reimbursement_resolution = reimbursement_resolution;
		this.reimbursement_author = reimbursement_author;
		this.reimbursement_resolver = reimbursement_resolver;
		this.reimbursement_status_id = reimbursement_status_id;
		this.reimbursement_type_id = reimbursement_type_id;
	}

	public Reimbursement(int reimbursement_amount, String reimbursement_description, int reimbursement_author,
			int reimbursement_type_id) {
		super();
		this.reimbursement_amount = reimbursement_amount;
		this.reimbursement_description = reimbursement_description;
		this.reimbursement_author = reimbursement_author;
		this.reimbursement_type_id = reimbursement_type_id;
	}

	public int getReimbursement_id() {
		return reimbursement_id;
	}

	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}

	public int getReimbursement_amount() {
		return reimbursement_amount;
	}

	public void setReimbursement_amount(int reimbursement_amount) {
		this.reimbursement_amount = reimbursement_amount;
	}

	public Timestamp getReimbursement_submitted() {
		return reimbursement_submitted;
	}

	public void setReimbursement_submitted(Timestamp reimbursement_submitted) {
		this.reimbursement_submitted = reimbursement_submitted;
	}

	public Timestamp getReimbursement_resolved() {
		return reimbursement_resolved;
	}

	public void setReimbursement_resolved(Timestamp reimbursement_resolved) {
		this.reimbursement_resolved = reimbursement_resolved;
	}

	public String getReimbursement_description() {
		return reimbursement_description;
	}

	public void setReimbursement_description(String reimbursement_description) {
		this.reimbursement_description = reimbursement_description;
	}

	public String getReimbursement_resolution() {
		return reimbursement_resolution;
	}

	public void setReimbursement_resolution(String reimbursement_resolution) {
		this.reimbursement_resolution = reimbursement_resolution;
	}

	public int getReimbursement_author() {
		return reimbursement_author;
	}

	public void setReimbursement_author(int reimbursement_author) {
		this.reimbursement_author = reimbursement_author;
	}

	public int getReimbursement_resolver() {
		return reimbursement_resolver;
	}

	public void setReimbursement_resolver(int reimbursement_resolver) {
		this.reimbursement_resolver = reimbursement_resolver;
	}

	public int getReimbursement_status_id() {
		return reimbursement_status_id;
	}

	public void setReimbursement_status_id(int reimbursement_status_id) {
		this.reimbursement_status_id = reimbursement_status_id;
	}

	public int getReimbursement_type_id() {
		return reimbursement_type_id;
	}

	public void setReimbursement_type_id(int reimbursement_type_id) {
		this.reimbursement_type_id = reimbursement_type_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbursement_amount;
		result = prime * result + reimbursement_author;
		result = prime * result + ((reimbursement_description == null) ? 0 : reimbursement_description.hashCode());
		result = prime * result + reimbursement_id;
		result = prime * result + ((reimbursement_resolution == null) ? 0 : reimbursement_resolution.hashCode());
		result = prime * result + ((reimbursement_resolved == null) ? 0 : reimbursement_resolved.hashCode());
		result = prime * result + reimbursement_resolver;
		result = prime * result + reimbursement_status_id;
		result = prime * result + ((reimbursement_submitted == null) ? 0 : reimbursement_submitted.hashCode());
		result = prime * result + reimbursement_type_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (reimbursement_amount != other.reimbursement_amount)
			return false;
		if (reimbursement_author != other.reimbursement_author)
			return false;
		if (reimbursement_description == null) {
			if (other.reimbursement_description != null)
				return false;
		} else if (!reimbursement_description.equals(other.reimbursement_description))
			return false;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		if (reimbursement_resolution == null) {
			if (other.reimbursement_resolution != null)
				return false;
		} else if (!reimbursement_resolution.equals(other.reimbursement_resolution))
			return false;
		if (reimbursement_resolved == null) {
			if (other.reimbursement_resolved != null)
				return false;
		} else if (!reimbursement_resolved.equals(other.reimbursement_resolved))
			return false;
		if (reimbursement_resolver != other.reimbursement_resolver)
			return false;
		if (reimbursement_status_id != other.reimbursement_status_id)
			return false;
		if (reimbursement_submitted == null) {
			if (other.reimbursement_submitted != null)
				return false;
		} else if (!reimbursement_submitted.equals(other.reimbursement_submitted))
			return false;
		if (reimbursement_type_id != other.reimbursement_type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursement_id=" + reimbursement_id + ", reimbursement_amount=" + reimbursement_amount
				+ ", reimbursement_submitted=" + reimbursement_submitted + ", reimbursement_resolved="
				+ reimbursement_resolved + ", reimbursement_description=" + reimbursement_description
				+ ", reimbursement_resolution=" + reimbursement_resolution + ", reimbursement_author="
				+ reimbursement_author + ", reimbursement_resolver=" + reimbursement_resolver
				+ ", reimbursement_status_id=" + reimbursement_status_id + ", reimbursement_type_id="
				+ reimbursement_type_id + "]";
	}
	
}
