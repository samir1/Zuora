
/**
 * @author Samir Undavia
 *
 */
public class Access implements Comparable<Access> {
	int id;
	int requirementId;
	int userId;

	/**
	 * @param id
	 * @param requirementId
	 * @param userId
	 */
	public Access(int id, int requirementId, int userId) {
		this.id = id;
		this.requirementId = requirementId;
		this.userId = userId;
	}
	
	/**
	 * @param requirementId
	 * @param userId
	 */
	public Access(int requirementId, int userId) {
		this.id = -1;
		this.requirementId = requirementId;
		this.userId = userId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the requirementId
	 */
	public int getRequirementId() {
		return requirementId;
	}

	/**
	 * @param requirementId the requirementId to set
	 */
	public void setRequirementId(int requirementId) {
		this.requirementId = requirementId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + userId;
		result = prime * result + requirementId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Access))
			return false;
		Access other = (Access) obj;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		if (requirementId != other.requirementId)
			return false;
		return true;
	}

	@Override
	public int compareTo(Access a) {
		return this.id - a.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Access [id=" + id + ", requirementId=" + requirementId
				+ ", userId=" + userId + "]";
	}
}
