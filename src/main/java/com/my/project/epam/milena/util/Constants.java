package com.my.project.epam.milena.util;

import java.util.regex.Pattern;

public final class Constants {

    private Constants() {
        throw new IllegalStateException();
    }

    public static final class SQLConstants {
        public static final String OBJECT_NAME = "java:comp/env/jdbc/shopdb";
        public static final String SAVE_USER = "INSERT INTO account (email,firstName,lastName,role,status,password) VALUES (?,?,?,?,?,?)";
        public static final String SAVE_PRODUCT = "INSERT INTO product (name,volume,color,price,manufacturer_id,create_date)" +
                " VALUES (?,?,?,?,?,?)";
        public static final String SAVE_MANUFACTURER = "INSERT INTO manufacturer (name) VALUES (?)";
        public static final String SAVE_ORDER = "INSERT INTO shop_order (status,address,number,account_id) VALUES (?,?,?,?)";
        public static final String GET_USER_BY_EMAIL = "SELECT * FROM account WHERE email = ?";
        public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
        public static final String GET_ALL_ORDERS = "SELECT * FROM shop_order";
        public static final String GET_ALL_USERS = "SELECT * FROM account";
        public static final String GET_VOLUME = "SELECT DISTINCT volume from product order by  volume asc";
        public static final String GET_COLOR = "SELECT DISTINCT color from product";
        public static final String GET_ALL_MANUFACTURERS = "SELECT * FROM manufacturer";
        public static final String GET_MANUFACTURER_BY_ID = "SELECT * FROM manufacturer WHERE id = ?";
        public static final String SAVE_ORDERED_PRODUCT = "INSERT INTO shop_order_has_product (shop_order_id,product_id,amount,price) VALUES(?,?,?,?)";
        public static final String UPDATE_STATUS_TO_BLOCKED = "UPDATE account SET status = 'BLOCKED' WHERE email = ?";
        public static final String UPDATE_STATUS_TO_UNBLOCKED = "UPDATE account SET status = 'UNBLOCKED' WHERE email = ?";
        public static final String UPDATE_STATUS_TO_REGISTERED = "UPDATE shop_order SET status = 'REGISTERED' WHERE id = ?";
        public static final String UPDATE_STATUS_TO_PAID = "UPDATE shop_order SET status = 'PAID' WHERE id = ?";
        public static final String UPDATE_STATUS_TO_CANCELED = "UPDATE shop_order SET status = 'CANCELED' WHERE id = ?";
        public static final String GET_MANUFACTURER_NY_NAME = "select * from manufacturer where name = ?";
        public static final String UPDATE_PRODUCT = "UPDATE product SET name=?,volume=?,color=?,price=?,manufacturer_id=?,create_date=? WHERE id = ";
        public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
        public static final String FOUND_ROWS = "SELECT FOUND_ROWS()";
        public static final String GET_ALL_USER_ORDERS = """
                SELECT s.id, s.status, s.address, s.number,
                    sp.amount,sp.price,
                    p.name
                FROM shop_order AS s
                    LEFT JOIN shop_order_has_product AS sp
                        ON s.id = sp.shop_order_id
                    LEFT JOIN product AS p
                        ON p.id = sp.product_id WHERE account_id =\s""";

        private SQLConstants() {
            throw new IllegalStateException();
        }
    }

    public static final class UserConstants {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String PASSWORD = "password";
        public static final String ERRORS = "errors";
        public static final String EMAIL_CONFIRMATION = "email_confirmation";
        public static final String LOGIN_CONFIRMATION = "login_confirmation";
        public static final String ROLE = "role";
        public static final String STATUS = "status";

        private UserConstants() {
            throw new IllegalStateException();
        }
    }

    public static final class RegexConstants {
        public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{4,20}$");
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        public static final Pattern VALID_FIRSTNAME_AND_LASTNAME_REGEX = Pattern.compile("\\p{L}");
        public static final Pattern VALID_CARD_NUMBER_REGEX = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");//visa card

        private RegexConstants() {
            throw new IllegalStateException();
        }
    }

    public static final class ErrorConstants {
        public static final String WRONG_EMAIL_FORMAT_MESSAGE = "**Wrong email format";
        public static final String WRONG_PASSWORD_FORMAT_MESSAGE = "**Password must be between 4 to 20 characters which contain at least one numeric digit and letter";
        public static final String WRONG_FIRST_NAME_FORMAT_MESSAGE = "**Wrong firstName format";
        public static final String WRONG_SECOND_NAME_FORMAT_MESSAGE = "**Wrong lastName format";
        public static final String NO_USER_WITH_SUCH_EMAIL = "**No user with such email";
        public static final String CAN_NOT_CLOSE_RESOURCE_MESSAGE = "Cannot close resource ";
        public static final String CAN_NOT_ROLLBACK_MESSAGE = "Cannot rollback transaction ";
        public static final String INCORRECT_PASSWORD_OR_EMAIL_MESSAGE = "**Incorrect password or email";
        public static final String EXISTING_EMAIL_MESSAGE = "**User with current email already exists";
        public static final String CAN_NOT_SAVE_USER_MESSAGE = "Cannot save user";
        public static final String CAN_NOT_GET_USER_MESSAGE = "Cannot get user by email";
        public static final String CAN_NOT_GET_ALL_PRODUCTS = "Cannot get all products";
        public static final String CAN_NOT_GET_ALL_PRODUCT_MANUFACTURES = "Cannot get all product manufactures";
        public static final String CAN_NOT_GET_MANUFACTURER_BY_ID = "Cannot get manufacturer by id";
        public static final String CAN_NOT_SAVE_ORDER_MESSAGE = "Cannot save order";
        public static final String CAN_NOT_SAVE_ORDERED_PRODUCT_MESSAGE = "Cannot save ordered product";
        public static final String WRONG_ADDRESS_FORMAT_MESSAGE = "**Wrong address format";
        public static final String WRONG_CARD_NUMBER_FORMAT_MESSAGE = "**Wrong card number format";
        public static final String CAN_NOT_GET_PRODUCT_BY_ID = "Cannot get product by id";
        public static final String CAN_NOT_GET_ALL_USERS = "Cannot get all users";
        public static final String CAN_NOT_SAVE_PRODUCT_MESSAGE = "Cannot save product";
        public static final String CANNOT_SAVE_MANUFACTURER_MESSAGE = "Cannot save product manufacturer";
        public static final String CAN_NOT_GET_MANUFACTURER_MESSAGE = "Cannot get manufacturer by name";
        public static final String CAN_NOT_GET_ALL_ORDERS = "Cannot get all orders";
        public static final String CAN_NOT_DELETE_PRODUCT = "Cannot delete product";
        public static final String CAN_NOT_TO_BLOCK_USER = "Cannot to block user";
        public static final String CAN_NOT_TO_UNBLOCK_USER = "Cannot to unblock user";
        public static final String CAN_NOT_UPDATE_STATUS_TO_REGISTERED = "Cannot update status to registered";
        public static final String CAN_NOT_UPDATE_STATUS_TO_PAID = "Cannot update status to paid";
        public static final String CAN_NOT_UPDATE_STATUS_TO_CANCELED = "Cannot update status to canceled";
        public static final String WRONG_PROCESS = "Wrong process ";
        public static final String CAN_NOT_GET_COLOR = "Cannot get color";
        public static final String CAN_NOT_GET_VOLUME = "Cannot get volume";
        public static final String CAN_NOT_GET_BRAND = "Cannot get brand";

        private ErrorConstants() {
            throw new IllegalStateException();
        }
    }

    public static final class AttributeConstants {
        public static final String USER_SERVICE_ATTRIBUTE = "userService";
        public static final String AUTH_USER_ATTRIBUTE = "authUser";
        public static final String USER_EMAIL_ATTRIBUTE = "userEmail";
        public static final String PRODUCTS_ATTRIBUTE = "products";
        public static final String PRODUCT_LIST = "productList";
        public static final String VOLUMES_ATTRIBUTE = "volumes";
        public static final String COLORS_ATTRIBUTE = "colors";
        public static final String BRANDS_ATTRIBUTE = "brands";
        public static final String PRODUCT_MANUFACTURER_SERVICE_ATTRIBUTE = "productManufacturerService";
        public static final String PRODUCT_SERVICE_ATTRIBUTE = "productService";
        public static final String PRODUCT_MANUFACTURER_NAME_LIST_ATTRIBUTE = "productManufacturerList";
        public static final String ORDER_SERVICE_ATTRIBUTE = "orderService";
        public static final String CART_SERVICE_ATTRIBUTE = "cartService";
        public static final String CABINET_SERVICE_ATTRIBUTE = "cabinetService";
        public static final String USERS = "users";
        public static final String PAGE = "page";
        public static final String USER_ORDERS_ATTRIBUTE = "userOrders";
        public static final String NUMBER_OF_PAGES = "numberOfPages";
        public static final String CURRENT_PAGE = "currentPage";
        public static final String NUMBER_OF_RECORDS = "noOfRecords";
        public static final String CART = "cart";
        public static final String TOTAL_SUM = "totalSum";


        private AttributeConstants() {
            throw new IllegalStateException();
        }
    }

    public static final class PathConstants {
        public static final String REGISTER_CONTROLLER = "RegisterController";
        public static final String LOGIN_CONTROLLER = "LoginController";
        public static final String ORDER_CONTROLLER = "orderController";
        public static final String CABINET_CONTROLLER = "/cabinet";
        public static final String LOGIN_REGISTER_JSP = "login_register.jsp";
        public static final String SHOP_PRODUCT_JSP = "shop.jsp";
        public static final String HOME = "index_shop.jsp";
        public static final String SUCCESS_PAGE = "successPage.jsp";
        public static final String MY_CABINET_JSP = "cabinet.jsp";
        public static final String SHOW_USERS = "showUsers.jsp";
        public static final String SHOW_PRODUCTS = "showProducts.jsp";
        public static final String SHOW_ORDERS = "showOrders.jsp";
        public static final String ADMIN = "admin.jsp";
        public static final String CHECKOUT = "checkout.jsp";
        public static final String ORDERS = "orders";
        public static final String CART_JSP = "cart.jsp";


        private PathConstants() {
            throw new IllegalStateException();
        }
    }

    public static final class ProductConstants {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String VOLUME = "volume";
        public static final String COLOR = "color";
        public static final String PRICE = "price";
        public static final String CREATE_DATE = "create_date";
        public static final String PRODUCT_MANUFACTURER_ID = "manufacturer_id";
        public static final String MANUFACTURER_ID = "manufacturerId";
        public static final String MANUFACTURER_NAME = "manufacturerName";
        public static final String AMOUNT = "amount";

        private ProductConstants() {
            throw new IllegalStateException();
        }
    }


    public static class OrderConstants {
        public static final String ORDER_ADDRESS = "address";
        public static final String ORDER_CARD_NUMBER = "cardNumber";
        public static final String ID = "id";
        public static final String STATUS = "status";
        public static final String ADDRESS = "address";
        public static final String NUMBER = "number";
        public static final String ACCOUNT_ID = "account_id";

        private OrderConstants() {
            throw new IllegalStateException();
        }
    }

    public static final class OtherConstants {
        public static final String ENCODING = "encoding";

        private OtherConstants() {
            throw new IllegalStateException();
        }
    }
}
