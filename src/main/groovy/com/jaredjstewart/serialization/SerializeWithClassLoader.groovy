package com.jaredjstewart.serialization

import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream


protected static String serialize(Serializable o) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(baos));

    oos.writeObject(o);
    oos.close();
    return Base64.getEncoder().encodeToString(baos.toByteArray());
}

/** Read the object from a Base64 string. */
protected static Object deserialize(String s) {
    byte[] data = Base64.getDecoder().decode(s);
    new GZIPInputStream(new ByteArrayInputStream(data)).withObjectInputStream(Thread.currentThread().getContextClassLoader()) { ois ->
        ois.readObject()
    }
}