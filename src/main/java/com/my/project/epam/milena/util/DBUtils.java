package com.my.project.epam.milena.util;

import java.util.logging.Logger;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.CAN_NOT_CLOSE_RESOURCE_MESSAGE;
/**
 * Util database methods
 *
 * @author Milena Bocharova
 */

public final class DBUtils {

    private static final Logger LOGGER = Logger.getLogger(DBUtils.class.getName());

    private DBUtils() {
        throw new IllegalStateException();
    }

    /**
     * Represents a storage object for some resource until it is closed
     * @param resources is resources that should be closed
     */
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    LOGGER.info(CAN_NOT_CLOSE_RESOURCE_MESSAGE + e.getMessage());
                }
            }
        }
    }
}