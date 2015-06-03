package com.jaredjstewart.serialization

import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream


static String serialize(Serializable o) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(baos));

    oos.writeObject(o);
    oos.close();
    return Base64.getEncoder().encodeToString(baos.toByteArray());
}

/** Read the object from a Base64 string. */
static Object deserialize(String s) {
    byte[] data = Base64.getDecoder().decode(s);
    new GZIPInputStream(new ByteArrayInputStream(data)).withObjectInputStream(Thread.currentThread().getContextClassLoader()) { ois ->
        ois.readObject()
    }
}

public static String compress(String str){
    compress(str.getBytes())
}


public static String compress(byte[] data){
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip = new GZIPOutputStream(out);
    gzip.write(data);
    gzip.close();
    return out.toString("ISO-8859-1");
}