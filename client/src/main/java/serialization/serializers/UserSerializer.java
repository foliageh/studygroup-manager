package serialization.serializers;

import models.User;
import serialization.exceptions.*;
import serialization.fields.*;

public class UserSerializer extends Serializer<User> {
    {
        fields.put("username", new StringField("Username", false));
        fields.put("password", new StringField("Password", false));
    }

    @Override
    public User toValue() {
        if (!isValid())
            throw new NotValidatedYetException("You should run validate() method first.");
        return new User((String) fields.get("username").toValue(),
                        (String) fields.get("password").toValue());
    }
}
