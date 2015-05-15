package com.jaredjstewart.serialization

import com.jaredjstewart.serialization.dummy_objects.DummyEmail
import com.jaredjstewart.serialization.dummy_objects.DummyMessage
import org.apache.commons.lang3.SerializationUtils

DummyEmail email = new DummyEmail(from: 'jared', to:'bob', body: new DummyMessage(html:true, body:"<b>Hellow world</b>"))
println "Email: ${email.dump()}"

encodedData = serialize(email)
println "Serialized base64 encoded email: ${encodedData}"



println "Deserialized email: ${email.dump()}"
DummyEmail deserializedEmail = (DummyEmail) deserialize(encodedData)

assert email == deserializedEmail




String serialize(Object object) {
    byte[] serializedData = SerializationUtils.serialize(object);
    String encodedData = Base64.getEncoder().encodeToString(serializedData);

    return encodedData
}

Object deserialize(String base64encodedSerialization) {
    byte[] decodedData = Base64.getDecoder().decode(base64encodedSerialization);
    return SerializationUtils.deserialize(decodedData);
}