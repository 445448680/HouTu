package me.zhyd.houtu.util;

/**
 * @author GHS
 * @version 1.0
 * @since 1.8
 */
public class CloseableUtil {

    /**
     * 关闭流
     *
     * @param closeables closeables
     */
    public static void close(AutoCloseable... closeables) {
        if (closeables != null) {
            for (AutoCloseable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
