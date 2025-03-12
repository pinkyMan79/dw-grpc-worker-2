package one.terenin.dwgrpcworkeer2.common;

import one.terenin.Protos;

import java.util.concurrent.ConcurrentLinkedQueue;

public class DataHolder {

    public static final ConcurrentLinkedQueue<Protos.DataBundle> jsonDataQueue = new ConcurrentLinkedQueue<>();
    public static final ConcurrentLinkedQueue<byte[]> parquetDataQueue = new ConcurrentLinkedQueue<>();

}
