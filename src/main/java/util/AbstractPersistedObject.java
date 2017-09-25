package util;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@MappedSuperclass
public abstract class AbstractPersistedObject extends BaseObject implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6581002097834461072L;

	@Id
	@GeneratedValue
	@XmlListAllAttribute
	private Long id;

	public Long getId() {
		return id;
	}

	@PostLoad
	private void postLoad() {
		LOG.debug("Loaded object from database", this);
	}

	@PostPersist
	private void postPersist() {
		LOG.debug("Persisted object on database", this);
	}

	@PostRemove
	public void postRemove() {
		LOG.debug("Removed object from database", this);
	}

	@PostUpdate
	private void postUpdate() {
		LOG.debug("Updated object on database", this);
	}

}
