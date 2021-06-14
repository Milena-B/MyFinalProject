package com.my.project.epam.milena.service.impl;

import com.my.project.epam.milena.dao.IUserDao;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.encrypt.Encoder;
import com.my.project.epam.milena.exceptions.UserException;
import com.my.project.epam.milena.service.IUserService;
import com.my.project.epam.milena.transaction.TransactionManager;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.INCORRECT_PASSWORD_OR_EMAIL_MESSAGE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.NO_USER_WITH_SUCH_EMAIL;

public class UserService implements IUserService {

    private final IUserDao userDao;
    private final TransactionManager transactionManager;

    public UserService(IUserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public User saveUser(User user) {
        return transactionManager.doGetTransactionOperation(() -> {
            if (Objects.isNull(userDao.getUserByEmail(user.getEmail()))) {
                return userDao.save(user);
            } else {
                return null;
            }
        });
    }

    @Override
    public List<User> getAll() {
        return transactionManager.doGetTransactionOperation(userDao::getAllUsers);
    }


    @Override
    public void updateStatusToBlocked(String email) {
        transactionManager.doModifiableTransactionOperation(() -> userDao.updateStatusToBlocked(email));
    }

    @Override
    public void updateStatusToUnBlocked(String email) {
        transactionManager.doModifiableTransactionOperation(() -> userDao.updateStatusToUnBlocked(email));
    }

    @Override
    public User checkUserOnExistenceAndPasswordOnMatch(User someUser) {
        return transactionManager.doGetTransactionOperation(() -> {
            var user = userDao.getUserByEmail(someUser.getEmail());
            if (Objects.isNull(user)) {
                throw new UserException(NO_USER_WITH_SUCH_EMAIL);
            }
            try {
                String password = Encoder.encrypt(someUser.getPassword());
                someUser.setPassword(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (!someUser.getPassword().equals(user.getPassword())) {
                throw new UserException(INCORRECT_PASSWORD_OR_EMAIL_MESSAGE);
            }
            return user;
        });
    }
    @Override
    public User getUserByEmail(String email){
       return transactionManager.doGetTransactionOperation(() -> userDao.getUserByEmail(email));
    }
}