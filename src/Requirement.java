/**
 * @author Samir Undavia
 *
 */
public class Requirement implements Comparable<Requirement> {
	private int id;
	private int parentRequirementId;

	/**
	 * @param id
	 * @param parentRequirementId
	 */
	public Requirement(int id, int parentRequirementId) {
		this.id = id;
		this.parentRequirementId = parentRequirementId;
	}

	/**
	 * @param parentRequirementId
	 */
	public Requirement(int parentRequirementId) {
		this.id = -1;
		this.parentRequirementId = parentRequirementId;
	}

	/**
	 * 
	 */
	public Requirement() {
		this.id = -1;
		this.parentRequirementId = -1;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the parentRequirementId
	 */
	public int getParentRequirementId() {
		return parentRequirementId;
	}

	/**
	 * @param parentRequirementId
	 *            the parentRequirementId to set
	 */
	public void setParentRequirementId(int parentRequirementId) {
		this.parentRequirementId = parentRequirementId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + parentRequirementId;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Requirement))
			return false;
		Requirement other = (Requirement) obj;
		if (id != other.id)
			return false;
		if (parentRequirementId != other.parentRequirementId)
			return false;
		return true;
	}

	public boolean isChildOf(int otherId) {
		return this.parentRequirementId == otherId;
	}

	@Override
	public int compareTo(Requirement r) {
		return this.id - r.getId();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Requirement [id=" + id + ", parentRequirementId="
				+ parentRequirementId + "]";
	}
}
