package com.nilunder.bdx;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.utils.TFText;

public class Instantiator {

	public GameObject newObject(JsonValue gobj){
		String type = gobj.get("type").asString();
		if (type.equals("CAMERA")){
			return new Camera();
		}
		if (type.equals("FONT")) {
			if (gobj.get("properties").has("truefont")) {
				return new TFText();
			}

            return new Text();
		}
		if (type.equals("LAMP")){
			return new Light();
		}
		return new GameObject();
	}
	
}
