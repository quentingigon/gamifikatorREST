package gamifikator.mongoconnection.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public class MongoDataObject {

	@Id
	@Property("id")
	private ObjectId id;

	public MongoDataObject() {
		super();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

}