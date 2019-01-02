package gamifikator.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public abstract class AbstractObject<PK> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private PK id;

	public AbstractObject() {
		super();
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}
}
